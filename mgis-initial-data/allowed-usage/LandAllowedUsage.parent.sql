-- SELECT *
-- FROM nc_land_allowed_usage u2
--   LEFT JOIN (SELECT
--                CASE WHEN position('.' IN number) > 0
--                  THEN regexp_replace(number, '\.\d+$', '')
--                ELSE '' END AS parent_number,
--                id          AS child_id,
--                number      AS child_number
--              FROM nc_land_allowed_usage u1) u3 ON u2.number = u3.parent_number

UPDATE nc_land_allowed_usage u4
SET parent_id = q1.parent_id
FROM (SELECT id AS parent_id
      FROM nc_land_allowed_usage u2
        LEFT JOIN (SELECT
                     CASE WHEN position('.' IN number) > 0
                       THEN regexp_replace(number, '\.\d+$', '')
                     ELSE '' END AS parent_number,
                     id          AS child_id,
                     number      AS child_number
                   FROM nc_land_allowed_usage u1) u3 ON u2.number = u3.parent_number) q1
WHERE q1.child_id = u4.id
