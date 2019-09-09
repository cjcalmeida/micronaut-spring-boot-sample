--
-- update
--
UPDATE movie
    SET title = :title, release_date = :release
WHERE id = :id
;