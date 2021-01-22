select * from person where
 id=?id
 {{ if eq 1 1 }}
   or  name = ?name
 {{ end}}
