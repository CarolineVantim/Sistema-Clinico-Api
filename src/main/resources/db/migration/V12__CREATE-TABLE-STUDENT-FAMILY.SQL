CREATE TABLE IF NOT EXISTS student_family (
    id SERIAL PRIMARY KEY,
    student_id VARCHAR(11) NOT NULL,
    family_id VARCHAR(11) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (cpf),
    FOREIGN KEY (family_id) REFERENCES family (cpf)
);
