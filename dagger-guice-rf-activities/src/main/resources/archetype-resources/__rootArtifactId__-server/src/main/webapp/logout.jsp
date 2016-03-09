<%@ page session="false" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset=UTF-8>
    <title>Web Application Starter Project</title>
    <link rel=stylesheet href="static/${rootArtifactId}.css">
  </head>
  <body>
<%!
    private String htmlEscape(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%-- Some servlet containers will clear the remote user when invalidating the session --%>
<%
    String userName = request.getRemoteUser();
%>
<%-- FIXME: use request.logout() instead, if you can use Servlets 3.0 --%>
<%
    HttpSession session = request.getSession(false);
    if (session != null) {
        session.invalidate();
    }
%>
    <h2>You've been logged out (<%= htmlEscape(userName) %>)</h2>
    <p>To log back in, <a href="./">click here</a>.
  </body>
</html>
