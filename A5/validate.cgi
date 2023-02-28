#!/usr/bin/python

import cgi, cgitb


form = cgi.FieldStorage()

usernameInData="mary"
passwordInData="mary123"

username = form.getvalue("username")
last_name = form.getvalue("password")

print("Content-Type: text/html;charset=utf-8\n\n")
print("<html>")
print("<head></head>")
print("<body>")
if(username == usernameInData and last_name == passwordInData):
	print("<h1>%s you have logged in.</h1>"%username)
	# print("%s You have logged in."%username)
else:
	print("<h1>Failed to log in.</h1>")
	# print("Failed to log in.")
print("</body>")
print("</html>")
