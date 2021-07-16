DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    job_title     VARCHAR(255) NOT NULL,
    photo_url     VARCHAR(255) NOT NULL
);

INSERT INTO employee (first_name, last_name, job_title, photo_url) VALUES
('Harry', 'Potter', 'Student', 'https://en.wikipedia.org/wiki/Harry_Potter'),
('Gandalf', 'Grey', 'Wizard', 'https://en.wikipedia.org/wiki/Gandalf'),
('Dale', 'Barbara', 'Colonel of US army', 'https://underthedome.fandom.com/wiki/Dale_Barbara_(TV_Series)');
