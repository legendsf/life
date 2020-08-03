<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>goods</title>
{{/*    <link rel="stylesheet" href="/static/css/index.css">*/}}
    {{assets_css "/static/css/index.css"}}
{{/*    {{assets_js "/static/js/alert.js"}}*/}}
</head>
<body>
    {{template "/public/header.html" .}}
    <h2>自定义模板函数
    </h2>
    {{.sessname}}
    {{.username}}
    {{ .k1sf }}
    {{ .port }}
    {{ .mtime }}
    {{unix2date .unix }}
    {{hi "aa"}}
    <h1 id="3">
        {{config "String" "httpport" "" }}
        <p>{{.html }}</p>

        <p>{{.html | html2str }}</p>
        <p>{{ html2str .html }}</p>

        <p>{{ str2html .html }}</p>
        <p>{{ .html | str2html }}</p>

        <p>{{ htmlquote .html }}</p>
        <p>{{ htmlunquote .html }}</p>

        <p>{{substr .title 0 5}}</p>
        goods <br/>
        <p>{{.title}}</p>
        <p>{{.goods.Id}}</p>
        <p>{{.goods.Name}}</p>
        {{$mtitle := .title}}
        {{$mtitle}}
        <ul>
            {{range $k,$v := .strList}}
            <li>{{$k}}--{{$v}}</li>
            {{end}}
        </ul>

        <ul>
            {{range $k,$v := .goodsMap}}
                <li>{{$k}}--{{$v}}</li>
            {{end}}
        </ul>
        hhhhhhhhhhhhhhhhhhhh
        {{map_get .goodsMap "k1"}}
        <ul>
            {{range $k,$v := .goodsList}}
                <li>{{$k}}--{{$v.Id}}--{{$v.Name}}</li>
            {{end}}
        </ul>
        {{if .isHome}}
            <p>在家</p>
        {{ else if .isSchool}}
            <p>在学校</p>
        {{end}}

        {{if gt .n1 .n2 }}
            <p>n1 大于 n2</p>
        {{end}}
        {{date .now "Y-m-d H:i:s"}}
    </h1>

    {{template "/public/footer.html"}}
</body>
</html>