<html>
<head>

    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>Wrapper HTML for App</title>

    <link type="text/css" rel="stylesheet" href="app.css">


    <!--                                            -->
    <!-- This script is required bootstrap stuff.   -->
    <!--                                            -->
    <script type="text/javascript" src="/_ah/channel/jsapi"></script>
    <script type="text/javascript" src="scripts.js"></script>
    <script type="text/javascript" language="javascript" src="app/app.nocache.js"></script>
</head>

<!--                                           -->
<!-- The body can have arbitrary html, or      -->
<!-- you can leave the body empty if you want  -->
<!-- to create a completely dynamic ui         -->
<!--                                           -->
<body>

<h1>Sample Application</h1>

<p>
    This is an example of a host page for the App application.
    You can attach a Web Toolkit module to any HTML page you like,
    making it easy to add bits of AJAX functionality to existing pages
    without starting from scratch.
</p>

<table align="center">
    <tr>
        <td id="slot1"></td><td id="slot2"></td><td id="slot3"><td id="slot4"></td>
    </tr>
</table>

<div width="20%" id="slot8"></div>
</body>
</html>