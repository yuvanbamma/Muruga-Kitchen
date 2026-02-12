-- FOOD_DONOR role
INSERT INTO roles (
    id,
    role_name,
    description,
    is_active,
    is_deleted,
    created_at,
    updated_at
)
SELECT
    UUID_TO_BIN(UUID()),
    'FOOD_DONOR',
    'User who donates food, clothes, or money to orphanages',
    b'1',
    b'0',
    NOW(6),
    NOW(6)
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE role_name = 'FOOD_DONOR'
);

-- ORPHANAGE role
INSERT INTO roles (
    id,
    role_name,
    description,
    is_active,
    is_deleted,
    created_at,
    updated_at
)
SELECT
    UUID_TO_BIN(UUID()),
    'ORPHANAGE',
    'Orphanage account that posts requirements and receives donations',
    b'1',
    b'0',
    NOW(6),
    NOW(6)
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE role_name = 'ORPHANAGE'
);
