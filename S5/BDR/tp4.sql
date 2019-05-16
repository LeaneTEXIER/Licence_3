--- AUTEUR : TEXIER, Léane
--- Q.1
--- EXISTS
SELECT acoul FROM articles a
WHERE NOT EXISTS (
  SELECT acoul FROM articles a2
  WHERE a.acoul=a2.acoul AND a.aid<>a2.aid);

--- Q.2
--- EXISTS
SELECT anom FROM articles a
WHERE acoul='rouge' AND NOT EXISTS (
   SELECT * FROM articles a2
   WHERE a.anom=a2.anom AND acoul='vert');
--- A/S
SELECT anom FROM articles a
WHERE acoul='rouge' AND a.anom <>ALL (
   SELECT anom FROM articles a2
   WHERE acoul='vert');
--- IN
SELECT anom FROM articles a
WHERE acoul='rouge' AND a.anom NOT IN(
   SELECT anom FROM articles a2
   WHERE acoul='vert');

--- Q.3
SELECT DISTINCT fnom FROM fournisseurs f NATURAL JOIN catalogue c NATURAL JOIN articles a
WHERE c.prix>ALL(
  SELECT avg(prix) FROM catalogue c2 NATURAL JOIN articles a2
  WHERE a.anom=a2.anom);

--- Q.4
--- EXISTS
SELECT DISTINCT anom, aid FROM articles a NATURAL JOIN catalogue c
WHERE EXISTS(
   SELECT * FROM catalogue c2
   WHERE c.aid=c2.aid AND c.fid<>c2.fid);

---- Q.5
--- IN
SELECT fnom FROM fournisseurs
WHERE fid NOT IN (
   SELECT fid FROM catalogue);
--- A/S
SELECT fnom FROM fournisseurs
WHERE fid <>ALL (
   SELECT fid FROM catalogue);

--- Q.6
SELECT anom FROM articles a NATURAL JOIN catalogue c NATURAL JOIN fournisseurs f
WHERE f.fnom = 'kiventout' AND NOT EXISTS(
   SELECT * FROM catalogue c2 NATURAL JOIN articles a2
   WHERE a.anom=a2.anom AND c.fid<>c2.fid);

--- Q.7
--- EXISTS
SELECT DISTINCT fnom FROM fournisseurs f NATURAL JOIN catalogue c
WHERE NOT EXISTS(
   SELECT fnom FROM fournisseurs f2 NATURAL JOIN catalogue c2
   WHERE f.fid<>f2.fid AND c.aid=c2.aid);

--- Q.8
--- EXISTS
SELECT fnom FROM fournisseurs NATURAL JOIN catalogue c
WHERE NOT EXISTS(
   SELECT * FROM catalogue c2
   WHERE c.prix<c2.prix);

--- Q.9
--- EXISTS
SELECT anom, min(prix), max(prix)
FROM articles a NATURAL JOIN catalogue c
WHERE EXISTS (
   SELECT * FROM articles a2 NATURAL JOIN catalogue c2
   WHERE c.fid<>c2.fid AND a.anom=a2.anom)
GROUP BY anom;

--- Q.10
--- EXISTS
SELECT DISTINCT fnom, anom FROM fournisseurs f NATURAL JOIN catalogue c NATURAL JOIN articles a
WHERE EXISTS(
   SELECT * FROM fournisseurs f2 NATURAL JOIN catalogue c2 NATURAL JOIN articles a2
   WHERE f.fnom=f2.fnom AND a.anom=a2.anom AND a.acoul<>a2.acoul);

--- Q.11
--- EXISTS
SELECT anom FROM articles a NATURAL JOIN catalogue c
WHERE NOT EXISTS(
   SELECT * FROM articles a2 NATURAL JOIN catalogue c2
   WHERE a.anom=a2.anom AND c.fid<>c2.fid);
--- GROUP BY
SELECT anom FROM articles a NATURAL JOIN catalogue c
GROUP BY anom
HAVING count(fid)=1;

--- Q.12
--- EXISTS
SELECT DISTINCT aid FROM catalogue c
WHERE c.prix>100 AND NOT EXISTS(
   SELECT * FROM catalogue c2
   WHERE c.aid=c2.aid and c2.prix<=100);
--- A/S
SELECT DISTINCT aid FROM catalogue c
WHERE c.prix>100 AND aid<>ALL(
   SELECT aid FROM catalogue c2
   WHERE c.aid=c2.aid and c2.prix<=100);

--- Q.13
--- EXISTS
SELECT aid FROM fournisseurs f NATURAL JOIN catalogue c
WHERE NOT EXISTS(
   SELECT * FROM fournisseurs f2 NATURAL JOIN catalogue c2
   WHERE c.aid = c2.aid AND f2.fad not like '%USA');

--- Q.14
SELECT fnom FROM fournisseurs NATURAL JOIN catalogue NATURAL JOIN articles
WHERE fnom<>ALL(
   SELECT fnom FROM fournisseurs NATURAL JOIN catalogue NATURAL JOIN articles
   WHERE acoul<>'rouge');

--- Q.15
SELECT DISTINCT fnom FROM fournisseurs f NATURAL JOIN catalogue c
WHERE fid=SOME(
   SELECT aid FROM articles a2 NATURAL JOIN catalogue c2
   WHERE a2.acoul='rouge' AND c.fid=c2.fid);
