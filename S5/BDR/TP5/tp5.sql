--- AUTEUR : TEXIER, Léane
--- Q.1
SELECT eid, max(portee) as portee_max
FROM Certifications NATURAL JOIN Avions
GROUP BY (eid)
HAVING count(*)>1;

--- Q.2
SELECT enom
FROM Employes NATURAL JOIN Certifications
WHERE salaire<(
   SELECT min(prix)
   FROM Vols
   WHERE dep='CDG' AND arr='NOU');

--- Q.3
--- EXISTS
SELECT dep, arr
FROM Vols v
WHERE NOT EXISTS(
   SELECT *
   FROM Employes e
   WHERE e.salaire>100000 AND EXISTS(
      SELECT *
      FROM Certifications c
      WHERE c.eid=e.eid)
   AND NOT EXISTS(
      SELECT *
      FROM Avions a, Certifications c2
      WHERE a.aid=c2.aid AND c2.eid=e.eid AND a.portee>=v.distance));

--- SANS EXISTS
SELECT dep, arr
FROM Vols
WHERE distance <=(
   SELECT min(max) FROM(
      SELECT max(portee) as max
      FROM Employes NATURAL JOIN Certifications NATURAL JOIN Avions
      GROUP BY eid
      HAVING salaire>100000) as portee_min);

--- Q.4
---GROUP BY et EVERY
SELECT enom
FROM Employes NATURAL JOIN Certifications NATURAL JOIN Avions
GROUP BY eid
HAVING every(portee>1500);

--- EXISTS
SELECT DISTINCT enom
FROM Employes e, Certifications c
WHERE c.eid=e.eid AND NOT EXISTS (
   SELECT *
   FROM Certifications c2
   WHERE c2.eid=e.eid AND NOT EXISTS (
      SELECT *
      FROM Avions a
      WHERE c2.aid=a.aid AND a.portee>1500));

--- Q.5
SELECT enom
FROM Employes NATURAL JOIN Certifications NATURAL JOIN Avions
GROUP BY enom
HAVING every(portee>1500) AND count(*)>1;

--- Q.6
SELECT enom
FROM Employes NATURAL JOIN Certifications NATURAL JOIN Vols NATURAL JOIN Avions
GROUP BY enom
HAVING every(portee>1500) AND bool_or(anom like 'Boeing %');

--- Q.7
SELECT eid
FROM Employes
WHERE salaire=(
   SELECT max(salaire)
   FROM Employes
   WHERE salaire<>(
      SELECT max(salaire)
      FROM Employes));

--- Q.8
SELECT enom
FROM Employes NATURAL JOIN Certifications NATURAL JOIN Avions
GROUP BY enom
HAVING bool_or(portee>2000) AND bool_and(anom not like 'Boeing %');

--- Q.9
SELECT enom, salaire
FROM Employes e
WHERE e.enom<>ALL(
   SELECT enom
   FROM Employes NATURAL JOIN Certifications)
AND e.salaire>(
  SELECT avg(salaire)
  FROM Employes e2
  WHERE e2.eid=SOME(
    SELECT eid
    FROM Employes NATURAL JOIN Certifications));

--- Q.10
SELECT avg(salaire) - (SELECT avg(salaire) FROM Employes) as difference
FROM Employes e2
WHERE e2.eid=SOME(
  SELECT eid
  FROM Employes NATURAL JOIN Certifications);

--- Q.11
SELECT h_dep
FROM Vols v1
WHERE dep='Madison'
AND ((arr='New York' AND extract(hour from v1.h_arr)<18)
OR EXISTS(
   SELECT *
   FROM Vols v2
   WHERE v1.arr=v2.dep AND v1.h_arr<=v2.h_dep
   AND ((v2.arr='New York' AND extract(hour from v2.h_arr)<18)
   OR EXISTS(
      SELECT *
      FROM Vols v3
      WHERE v2.arr=v3.dep AND v2.h_arr<=v3.h_dep
      AND v3.arr='New York' AND extract(hour from v3.h_arr)<18))));
