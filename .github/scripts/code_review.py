import os
import requests
import json

def post_code_for_review(server_url, code):
    headers = {'Content-Type': 'application/json'}
    data = {'code': code}
    response = requests.post(server_url, headers=headers, data=json.dumps(data))
    response.raise_for_status()
    return response.json()

def post_github_comment(pr_number, comment):
    token = os.getenv('GITHUB_TOKEN')
    repo = os.getenv('GITHUB_REPOSITORY')

    url = f"https://api.github.com/repos/{repo}/issues/{pr_number}/comments"
    headers = {
        'Authorization': f'token {token}',
        'Content-Type': 'application/json'
    }
    payload = {
        'body': comment
    }

    response = requests.post(url, headers=headers, json=payload)
    response.raise_for_status()

def main():
    server_url = os.getenv('SERVER_URL')
    pr_number = os.getenv('GITHUB_PR_NUMBER')

    # 변경된 파일 목록 가져오기
    changed_files = os.popen('git diff --name-only origin/main').read().splitlines()

    review_results = {
        'runtime_errors': [],
        'performance_issues': [],
        'security_issues': []
    }

    for file_path in changed_files:
        if file_path.endswith('.py'):
            with open(file_path, 'r') as file:
                code = file.read()
            file_review_results = post_code_for_review(server_url, code)
            review_results['runtime_errors'].extend(file_review_results['runtime_errors'])
            review_results['performance_issues'].extend(file_review_results['performance_issues'])
            review_results['security_issues'].extend(file_review_results['security_issues'])

    comment = "### 코드 리뷰 결과\n"

    comment += "#### 런타임 에러\n"
    for error in review_results['runtime_errors']:
        comment += f"- Line {error['line_number']}: {error['comment']}\n"

    comment += "\n#### 성능 개선\n"
    for issue in review_results['performance_issues']:
        comment += f"- Line {issue['line_number']}: {issue['comment']}\n"

    comment += "\n#### 보안적 이슈\n"
    for issue in review_results['security_issues']:
        comment += f"- Line {issue['line_number']}: {issue['comment']}\n"

    post_github_comment(pr_number, comment)

if __name__ == "__main__":
    main()