SELECT * FROM PRODUCT P WHERE lower(P.DESCRIPTION) LIKE '%rod%' and lower(P.MANUFACTURER) like '%owner%' ;

SELECT * FROM REGION R
WHERE
    R.ID IN (2,3) AND R.PRODUCT_ID
                        IN (SELECT
                                   P.ID FROM PRODUCT P
                            WHERE
                                lower(P.DESCRIPTION) LIKE '%od%' and
                                lower(P.MANUFACTURER) like '%owner%')
ORDER BY R.DESCRIPTION ASC ;

SELECT * FROM PRODUCT_LINE PL
WHERE
    PL.ID IN (2,3) AND PL.PRODUCT_ID
                        IN (SELECT
                                   P.ID FROM PRODUCT P
                            WHERE
                                lower(P.DESCRIPTION) LIKE '%od%' and
                                lower(P.MANUFACTURER) like '%owner%')
ORDER BY PL.DESCRIPTION ASC ;