CREATE TABLE IF NOT EXISTS field_of_works (
    id SERIAL PRIMARY KEY,
    professional_crm VARCHAR(20),
    field_of_work VARCHAR(255),
    FOREIGN KEY (professional_crm) REFERENCES professional(crm)
);