CREATE TABLE IF NOT EXISTS states (
                                      id UUID PRIMARY KEY,
                                      state_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
    );

CREATE TABLE IF NOT EXISTS cities (
                                      id UUID PRIMARY KEY,
                                      city_name VARCHAR(100) NOT NULL,
    pin_code VARCHAR(6),
    state_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT now()
    );

INSERT INTO states (id, state_name)
VALUES
    (gen_random_uuid(), 'Tamil Nadu'),
    (gen_random_uuid(), 'Kerala'),
    (gen_random_uuid(), 'Karnataka'),
    (gen_random_uuid(), 'Andhra Pradesh');

-- Tamil Nadu
INSERT INTO cities (id, city_name, pin_code, state_id)
SELECT gen_random_uuid(), 'Chennai', '600001', id
FROM states WHERE state_name = 'Tamil Nadu';

INSERT INTO cities (id, city_name, pin_code, state_id)
SELECT gen_random_uuid(), 'Coimbatore', '641001', id
FROM states WHERE state_name = 'Tamil Nadu';

INSERT INTO cities (id, city_name, pin_code, state_id)
SELECT gen_random_uuid(), 'Pollachi', '642001', id
FROM states WHERE state_name = 'Tamil Nadu';

INSERT INTO cities (id, city_name, pin_code, state_id)
SELECT gen_random_uuid(), 'Madurai', '625001', id
FROM states WHERE state_name = 'Tamil Nadu';


-- Kerala
INSERT INTO cities (id, city_name, pin_code, state_id)
SELECT gen_random_uuid(), 'Kochi', '682001', id
FROM states WHERE state_name = 'Kerala';

INSERT INTO cities (id, city_name, pin_code, state_id)
SELECT gen_random_uuid(), 'Trivandrum', '695001', id
FROM states WHERE state_name = 'Kerala';