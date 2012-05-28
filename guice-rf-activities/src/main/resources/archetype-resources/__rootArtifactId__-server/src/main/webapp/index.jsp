<%@ page session="false" contentType="text/html;charset=utf-8" %>
<!doctype html>
<html>
  <head>
    <meta charset=UTF-8>

    <%--                                                               --%>
    <%-- Consider inlining CSS to reduce the number of requested files --%>
    <%--                                                               --%>
    <link type="text/css" rel="stylesheet" href="${rootArtifactId}.css">

    <%--                                           --%>
    <%-- Any title is fine                         --%>
    <%--                                           --%>
    <title>Web Application Starter Project</title>

    <%-- Tell GWT where to find its permutations, as we inline the *.nocache.js --%>
    <meta name="${module-short-name}::gwt:property" content='baseUrl=${module-short-name}/'>
  </head>

  <body>
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled in order for this application to display correctly.
      </div>
    </noscript>

    <script>
        var user = '<%= request.getRemoteUser()  %>';
        var admin = <%= request.isUserInRole("admin") ? "true" : "false" %>;
    </script>
    <%-- OPTIONAL: include this if you want history support in IE6 and IE7 --%>
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>

    <script>
        <%@ include file="${module-short-name}/${module-short-name}.nocache.js" %>
    </script>
  </body>
</html>
