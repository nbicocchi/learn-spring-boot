INSERT INTO Project(id, code, name, description) VALUES (default, 'P1', 'Project 1', 'Description of Project 1');
INSERT INTO Project(id, code, name, description) VALUES (default, 'P2', 'Project 2', 'About Project 2');
INSERT INTO Project(id, code, name, description) VALUES (default, 'P3', 'Project 3', 'About Project 3');

INSERT INTO Worker(id, email, first_name, last_name) VALUES (default, 'john@test.com', 'John', 'Doe');

INSERT INTO Task(id, uuid, name, due_date, description, project_id, status) VALUES (default, 'ab67df2d-4d6c-4854-9349-5c3b382a7221', 'Task 1', '2025-01-12', 'Task 1 Description', 1, 0);
INSERT INTO Task(id, uuid, name, due_date, description, project_id, status) VALUES (default, '494643fc-0972-42bd-83e2-80664d04333b', 'Task 2', '2025-02-10', 'Task 2 Description', 1, 0);
INSERT INTO Task(id, uuid, name, due_date, description, project_id, status) VALUES (default, 'd64a70eb-d6fc-4357-b663-6d248e7dae3f', 'Task 3', '2025-03-16', 'Task 3 Description', 1, 0);
INSERT INTO Task(id, uuid, name, due_date, description, project_id, status, assignee_id) VALUES (default, '3badd27b-38e7-4b90-8c6e-20c8166ad9f4', 'Task 4', '2025-06-25', 'Task 4 Description', 2, 0, 1);