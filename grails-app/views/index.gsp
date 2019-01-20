<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to Grails</h1>

            <div id="controllers" role="navigation">
                <h2>Available Farms:</h2>
                <ul>
                                         <g:each var="f" in="${farms}">
                        <li class="controller">
                                                         <g:link controller="farm" action="select" id="${f.name}">${f.name}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </section>
    </div>

</body>
</html>
