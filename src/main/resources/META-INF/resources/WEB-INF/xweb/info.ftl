<!DOCTYPE html>
<#assign base_url = "${xconfig('base_url')}" />
<html lang="zh_CN">
<head>
    <title>${title}</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <link href="${base_url}/xlibs/css/bootstrap.css" rel="stylesheet" media="screen"/>
    <link rel="shortcut icon" type="image/x-icon" href="${base_url}/favicon.ico"/>
    <script type="text/javascript" src="${base_url}/xlibs/js/jquery-v2x.js"></script>
    <script type="text/javascript" src="${base_url}/xlibs/js/bootstrap.js"></script>
    <style>
        body {
        background-color: #f5f5f5
        }
        .center {
            text-align: center;
            margin-left: auto;
            margin-right: auto;
            margin-bottom: auto;
            margin-top: auto;
        }
    </style>
</head>
<body>
    <div id="wrapper">
        <div class="center">
            <h1>${prompt}</h1> 
            <a href="${base_url}/index.htm">返回首页</a> 
        </div>
    </div>
    <div id="footer" class="center">
        <@include file="copyright.ftl" />
    </div>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#wrapper').css({
                'margin-top': function () { 
                    return (($(document).height() - $(this).height()) / 2); 
                },
            });
            $('#footer').css({
                'margin-top': function () {
                    var h = (($(document).height() - $('#wrapper').height()) / 2); 
                    return (h - $(this).height()); 
                }, 
            });
        }); 
    </script>
</body>
</html>
