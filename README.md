## Take home test submission

contact: rafi.nizar15@gmail.com / +6281218593545

summary: an event crud application with simple jwt auth

## Todos

- [x] jwt basic setup (access token only)
- [x] auth endpoints
    - [x] POST /api/auth/signup
    - [x] POST /api/auth/signin
- [x] events end points
    - [x] POST /api/events
    - [x] GET /api/events
    - [x] GET /api/events/{id}
    - [x] POST /api/events/{id}/register
    - [x] GET /api/events/stats

## Database setup

initial setup

```sql
-- create db user (connection)
CREATE USER bear WITH PASSWORD 'clairebear';
GRANT USAGE on schema public to bear;
GRANT SELECT, UPDATE, INSERT, DELETE ON ALL TABLES IN SCHEMA public TO bear;

-- insert roles
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

dummies for stats

```sql
-- dummy users role user
insert into users (id, name, password, email) values (201, 'john doe', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'john.doe1@email.com');
insert into user_roles(user_id, role_id) values (201, 1);

insert into users (id, name, password, email) values (202, 'sarah johnson', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'sarah.johnson@email.com');
insert into user_roles(user_id, role_id) values (202, 1);

insert into users (id, name, password, email) values (203, 'mike chen', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'mike.chen@email.com');
insert into user_roles(user_id, role_id) values (203, 1);

insert into users (id, name, password, email) values (204, 'emily davis', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'emily.davis@email.com');
insert into user_roles(user_id, role_id) values (204, 1);

insert into users (id, name, password, email) values (205, 'alex rodriguez', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'alex.rodriguez@email.com');
insert into user_roles(user_id, role_id) values (205, 1);

insert into users (id, name, password, email) values (206, 'lisa wang', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'lisa.wang@email.com');
insert into user_roles(user_id, role_id) values (206, 1);

insert into users (id, name, password, email) values (207, 'david smith', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'david.smith@email.com');
insert into user_roles(user_id, role_id) values (207, 1);

insert into users (id, name, password, email) values (208, 'maria garcia', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'maria.garcia@email.com');
insert into user_roles(user_id, role_id) values (208, 1);

insert into users (id, name, password, email) values (209, 'james wilson', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'james.wilson@email.com');
insert into user_roles(user_id, role_id) values (209, 1);

insert into users (id, name, password, email) values (210, 'anna lee', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'anna.lee@email.com');
insert into user_roles(user_id, role_id) values (210, 1);

insert into users (id, name, password, email) values (211, 'kevin brown', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'kevin.brown@email.com');
insert into user_roles(user_id, role_id) values (211, 1);

insert into users (id, name, password, email) values (212, 'rachel taylor', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'rachel.taylor@email.com');
insert into user_roles(user_id, role_id) values (212, 1);

insert into users (id, name, password, email) values (213, 'ryan martinez', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'ryan.martinez@email.com');
insert into user_roles(user_id, role_id) values (213, 1);

insert into users (id, name, password, email) values (214, 'jessica kim', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'jessica.kim@email.com');
insert into user_roles(user_id, role_id) values (214, 1);

insert into users (id, name, password, email) values (215, 'thomas anderson', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'thomas.anderson@email.com');
insert into user_roles(user_id, role_id) values (215, 1);

insert into users (id, name, password, email) values (216, 'stephanie white', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'stephanie.white@email.com');
insert into user_roles(user_id, role_id) values (216, 1);

insert into users (id, name, password, email) values (217, 'daniel jackson', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'daniel.jackson@email.com');
insert into user_roles(user_id, role_id) values (217, 1);

insert into users (id, name, password, email) values (218, 'natalie thompson', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'natalie.thompson@email.com');
insert into user_roles(user_id, role_id) values (218, 1);

insert into users (id, name, password, email) values (219, 'christopher moore', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'christopher.moore@email.com');
insert into user_roles(user_id, role_id) values (219, 1);

insert into users (id, name, password, email) values (220, 'amanda clark', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'amanda.clark@email.com');
insert into user_roles(user_id, role_id) values (220, 1);

insert into users (id, name, password, email) values (221, 'brandon lewis', '$2a$10$9vtQqNfFs69CsVg3VfN6y.r/wgexem5micBu1ez/BOODOr.nQrudS', 'brandon.lewis@email.com');
insert into user_roles(user_id, role_id) values (221, 1);

-- dummy events
insert into events (id, event_date, description, title, created_by) values (200, '2026-01-15 14:30:00.000', 'Annual meeting for strategic planning', 'Company Meeting', 1);
insert into events (id, event_date, description, title, created_by) values (201, '2026-02-20 09:00:00.000', 'Monthly team building activities', 'Team Building', 2);
insert into events (id, event_date, description, title, created_by) values (202, '2026-03-10 16:45:00.000', 'Product launch presentation event', 'Product Launch', 1);
insert into events (id, event_date, description, title, created_by) values (203, '2026-04-05 11:15:00.000', 'Technical training for dev team', 'Tech Training', 2);
insert into events (id, event_date, description, title, created_by) values (204, '2026-05-18 13:20:00.000', 'Customer feedback review meeting', 'Feedback Review', 1);
insert into events (id, event_date, description, title, created_by) values (205, '2026-06-22 10:30:00.000', 'Quarterly business review session', 'Q2 Review', 2);
insert into events (id, event_date, description, title, created_by) values (206, '2026-07-08 15:00:00.000', 'Summer company picnic celebration', 'Summer Picnic', 1);
insert into events (id, event_date, description, title, created_by) values (207, '2026-08-12 08:45:00.000', 'New employee orientation program', 'New Employee Intro', 2);
insert into events (id, event_date, description, title, created_by) values (208, '2026-09-25 17:30:00.000', 'Industry conference attendance', 'Industry Conference', 1);
insert into events (id, event_date, description, title, created_by) values (209, '2026-10-14 12:00:00.000', 'Monthly departmental meeting', 'Dept Meeting', 2);
insert into events (id, event_date, description, title, created_by) values (210, '2026-11-03 14:15:00.000', 'Performance review discussions', 'Performance Reviews', 1);
insert into events (id, event_date, description, title, created_by) values (211, '2026-12-20 16:00:00.000', 'Holiday celebration party', 'Holiday Party', 2);
insert into events (id, event_date, description, title, created_by) values (212, '2026-01-28 09:30:00.000', 'Budget planning session', 'Budget Planning', 1);
insert into events (id, event_date, description, title, created_by) values (213, '2026-02-14 11:45:00.000', 'Client presentation meeting', 'Client Presentation', 2);
insert into events (id, event_date, description, title, created_by) values (214, '2026-03-30 13:10:00.000', 'Safety training workshop', 'Safety Training', 1);
insert into events (id, event_date, description, title, created_by) values (215, '2026-04-17 15:25:00.000', 'Marketing campaign review', 'Marketing Review', 2);
insert into events (id, event_date, description, title, created_by) values (216, '2026-05-09 10:20:00.000', 'Software update deployment', 'Software Update', 1);
insert into events (id, event_date, description, title, created_by) values (217, '2026-06-11 14:50:00.000', 'Vendor meeting and negotiation', 'Vendor Meeting', 2);
insert into events (id, event_date, description, title, created_by) values (218, '2026-07-23 12:35:00.000', 'Mid-year review session', 'Mid-Year Review', 1);
insert into events (id, event_date, description, title, created_by) values (219, '2026-08-29 16:20:00.000', 'Innovation brainstorming session', 'Innovation Workshop', 2);

-- dummy registration event
insert into event_registrations (id, registration_date, event_id, user_id) values (102, '2025-12-15 08:30:15.123', 200, 202);
insert into event_registrations (id, registration_date, event_id, user_id) values (103, '2025-12-16 14:22:45.456', 200, 207);
insert into event_registrations (id, registration_date, event_id, user_id) values (104, '2025-12-17 09:15:33.789', 200, 212);
insert into event_registrations (id, registration_date, event_id, user_id) values (105, '2025-12-18 16:45:22.012', 200, 217);
insert into event_registrations (id, registration_date, event_id, user_id) values (106, '2025-12-19 11:30:18.345', 200, 221);
insert into event_registrations (id, registration_date, event_id, user_id) values (107, '2025-12-20 13:25:55.678', 203, 203);
insert into event_registrations (id, registration_date, event_id, user_id) values (108, '2025-12-21 10:18:40.901', 203, 208);
insert into event_registrations (id, registration_date, event_id, user_id) values (109, '2025-12-22 15:50:12.234', 203, 213);
insert into event_registrations (id, registration_date, event_id, user_id) values (110, '2025-12-23 12:40:28.567', 203, 218);
insert into event_registrations (id, registration_date, event_id, user_id) values (111, '2025-12-24 17:35:45.890', 203, 204);
insert into event_registrations (id, registration_date, event_id, user_id) values (112, '2025-12-25 09:20:33.123', 203, 209);
insert into event_registrations (id, registration_date, event_id, user_id) values (113, '2025-12-26 14:15:20.456', 206, 214);
insert into event_registrations (id, registration_date, event_id, user_id) values (114, '2025-12-27 11:55:18.789', 206, 219);
insert into event_registrations (id, registration_date, event_id, user_id) values (115, '2025-12-28 16:10:35.012', 206, 205);
insert into event_registrations (id, registration_date, event_id, user_id) values (116, '2025-12-29 13:45:22.345', 206, 210);
insert into event_registrations (id, registration_date, event_id, user_id) values (117, '2025-12-30 10:30:15.678', 210, 215);
insert into event_registrations (id, registration_date, event_id, user_id) values (118, '2025-12-31 15:25:40.901', 210, 220);
insert into event_registrations (id, registration_date, event_id, user_id) values (119, '2026-01-01 12:20:28.234', 210, 206);
insert into event_registrations (id, registration_date, event_id, user_id) values (120, '2026-01-02 17:15:55.567', 215, 211);
insert into event_registrations (id, registration_date, event_id, user_id) values (121, '2026-01-03 14:50:12.890', 215, 216);
```

## API Specs

Swagger UI (OpenAPI) available at http://localhost:8080/swagger-ui/index.html

## Curl examples

### auth api

- sign up

```bash
curl --request POST \
  --url http://localhost:8080/api/auth/signup \
  --header 'content-type: application/json' \
  --data '{
  "name": "rafi user again",
  "email": "rafi1@user.com",
  "password": "passsowrd",
  "role": ["user"]
}'
```

- sign in
```bash
curl --request POST \
  --url http://localhost:8080/api/auth/signin \
  --header 'content-type: application/json' \
  --data '{
  "email": "rafi@admin.com",
  "password": "rafinizar"
}'
```

### event api

- get all events

```bash
curl --request GET \
  --url 'http://localhost:8080/api/events?title=event' \
  --header 'content-type: application/json'
```

- get event by id

```bash
curl --request GET \
  --url http://localhost:8080/api/events/100000 \
  --header 'content-type: application/json'
```

- create event (admin)

```bash
curl --request POST \
  --url http://localhost:8080/api/events \
  --header 'authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWZpQGFkbWluLmNvbSIsImlhdCI6MTc1MTYzNjQ2MywiZXhwIjoxNzUxNzIyODYzfQ.ASC0dmeIC-P2K1xKruVUyxGG0U86fLooVseeX_YVjqw' \
  --header 'content-type: application/json' \
  --data '{
  "title": "event 6",
  "description": "desc",
  "date": "01/12/2024 10:00:00"
}'
```

- user register to event

```bash
curl --request POST \
  --url http://localhost:8080/api/events/1/register \
  --header 'authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWZpQGVtYWlsLmNvbSIsImlhdCI6MTc1MTYzNTczMiwiZXhwIjoxNzUxNzIyMTMyfQ.Fbvsoys8FW9RkMSoSUnudwEh-ceApOI-uAojzA05q6s'
```

- events stat

```bash
curl --request GET \
  --url http://localhost:8080/api/events/stats
```
