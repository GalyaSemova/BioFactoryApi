# INSERT INTO `bio_store`.`user_roles`(id, role)
# VALUES
#     (1, 'USER'),
#     (1, 'ADMIN'),
#     (1, 'MODERATOR'),
#     (2, 'USER'),
#     (2, 'ADMIN');

# INSERT INTO `bio_store`.`user_roles`(id, role)
# VALUES
#     (3, 'MODERATOR');

INSERT INTO `bio_store`.`users_roles` (user_entity_id, roles_id)
VALUES
    (1,1),
    (1,2),
    (1,3),
    (2,2);