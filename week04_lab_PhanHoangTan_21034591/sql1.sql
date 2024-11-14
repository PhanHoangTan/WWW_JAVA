USE lab05;

CREATE TABLE Candidate (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    last_Name VARCHAR(255),
    middle_Name VARCHAR(255),
    first_Name VARCHAR(255),
    full_Name VARCHAR(255) GENERATED ALWAYS AS (CONCAT(last_Name, ' ', middle_Name, ' ', first_Name)) STORED,
    dob VARCHAR(255),
    email VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255)
);

CREATE TABLE Skill (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    skill_Name VARCHAR(255),
    description VARCHAR(255),
    field VARCHAR(255)
);

CREATE TABLE Job (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255)
);

CREATE TABLE Candidate_Skill (
    candidate_id BIGINT,
    skill_id BIGINT,
    level VARCHAR(255),
    PRIMARY KEY (candidate_id, skill_id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate(id),
    FOREIGN KEY (skill_id) REFERENCES Skill(id)
);

CREATE TABLE Job_Skill (
    job_id BIGINT,
    skill_id BIGINT,
    level VARCHAR(255),
    PRIMARY KEY (job_id, skill_id),
    FOREIGN KEY (job_id) REFERENCES Job(id),
    FOREIGN KEY (skill_id) REFERENCES Skill(id)
);