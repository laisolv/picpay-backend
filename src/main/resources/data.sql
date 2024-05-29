DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

INSERT INTO
    WALLETS (
        ID,
        FULL_NAME,
        CPF,
        EMAIL,
        "PASSWORD",
        "TYPE",
        BALANCE
    )
VALUES (
        1,
        'Lais - User',
        '12345678900',
        'lais@test.com',
        '123456',
        1,
        1000.00
    );

INSERT INTO
    WALLETS (
        ID,
        FULL_NAME,
        CPF,
        EMAIL,
        "PASSWORD",
        "TYPE",
        BALANCE
    )
VALUES (
        2,
        'Oliveira - Lojista',
        12345678901,
        'oliveira@test.com',
        '123456',
        2,
        1000.00
    );