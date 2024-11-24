CREATE TABLE IF NOT EXISTS professional_student (
      professional_id VARCHAR(20),
      student_id VARCHAR(11),
      FOREIGN KEY (student_id) REFERENCES student(cpf),
      FOREIGN KEY (professional_id) REFERENCES professional(crm)
);