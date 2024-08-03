-- キー・スペースの作成
CREATE KEYSPACE IF NOT EXISTS research WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

-- キー・スペースの使用
USE research;

-- テーブルの作成
CREATE TABLE IF NOT EXISTS papers (
    paper_id INT PRIMARY KEY,
    member_ids LIST<INT>,
    conference_id INT,
    title TEXT,
    publication_date DATE,
    cited_number INT,
    project_link TEXT,
    related_fields TEXT
);

-- データの挿入
INSERT INTO papers (paper_id, member_ids, conference_id, title, publication_date, cited_number, project_link, related_fields)
VALUES (0, [0, 1, 3], 2, 'Deep Learning Approaches for Predictive Analytics in Healthcare', '2023-05-04', 86, 'https://example.com/projects/fish-mother-safe', '');

INSERT INTO papers (paper_id, member_ids, conference_id, title, publication_date, cited_number, project_link, related_fields)
VALUES (1, [3, 4], 1, 'Reinforcement Learning for Autonomous Driving: A Survey', '2023-05-21', 84, 'https://example.com/projects/various-defense', '');

INSERT INTO papers (paper_id, member_ids, conference_id, title, publication_date, cited_number, project_link, related_fields)
VALUES (2, [4, 5], 0, 'Applications of Transfer Learning in Natural Language Processing', '2020-05-15', 80, 'https://example.com/projects/reflect-card-heavy', '');

INSERT INTO papers (paper_id, member_ids, conference_id, title, publication_date, cited_number, project_link, related_fields)
VALUES (3, [1, 2, 7], 2, 'Comparative Study of Supervised and Unsupervised Learning Techniques', '2024-02-16', 17, 'https://example.com/projects/nearly-middle', '');

INSERT INTO papers (paper_id, member_ids, conference_id, title, publication_date, cited_number, project_link, related_fields)
VALUES (4, [4, 7, 8], 4, 'Neural Network Architectures for Image Classification Tasks', '2022-02-23', 93, 'https://example.com/projects/foreign-staff', '');