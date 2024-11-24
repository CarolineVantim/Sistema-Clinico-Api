CREATE TABLE IF NOT EXISTS video_records (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    id_google_drive_media INT,
    recording_date DATE NOT NULL DEFAULT CURRENT_DATE,
    description TEXT,
    video_url VARCHAR(255),
    FOREIGN KEY (id_google_drive_media) REFERENCES google_drive_media(id) on delete set null
);