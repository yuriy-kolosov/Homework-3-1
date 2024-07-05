-- liquibase formatted sql

-- changeset yuriy-kolosov:1
CREATE INDEX student_name_index ON student (name)

-- changeset yuriy-kolosov:2
CREATE INDEX faculty_name_color_index ON faculty (name, color)