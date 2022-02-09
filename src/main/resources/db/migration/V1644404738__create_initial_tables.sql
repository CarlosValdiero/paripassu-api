CREATE TABLE current_password (
    uuid_sequence uuid NOT NULL,
    Password_type int2 NOT NULL,
    value INT NOT NULL,
    PRIMARY KEY (uuid_sequence)
);

CREATE TABLE password_sequence (
    uuid uuid NOT NULL,
    Password_type int2 NOT NULL,
    value INT NOT NULL,
    current_value INT NOT NULL,
    PRIMARY KEY (uuid)
);