INSERT INTO roles (role) VALUES ('USER');
INSERT INTO roles (role) VALUES ('ADMIN');
INSERT INTO roles (role) VALUES ('SUPER_ADMIN');

INSERT INTO internationalization (word_en, word_ru, group_enum, key_word) VALUES ('Login You have not yet confirmed your email address. Please check your email for a confirmation link.', '', 'messages', 'check_email');
INSERT INTO internationalization (word_en, word_ru, group_enum, key_word) VALUES ('Login Your account has been blocked by admin. Please contact v.koshman94@gmail.com.', '', 'messages', 'blocked');
INSERT INTO internationalization (word_en, word_ru, group_enum, key_word) VALUES ('Login Your account has been deleted. If you believe this is in error, please contact v.koshman94@gmail.com.', '', 'messages', 'deleted');
INSERT INTO internationalization (word_en, word_ru, group_enum, key_word) VALUES ('Login The email address you specified doesn''t exist.', '', 'messages', 'email_not_exist');
INSERT INTO internationalization (word_en, word_ru, group_enum, key_word) VALUES ('Login You have not yet confirmed your email address. Please check your email for a confirmation link.', '', 'messages', 'confirm_email');
INSERT INTO internationalization (word_en, word_ru, group_enum, key_word) VALUES ('Sing Up The email address you have entered is already registered.', '', 'messages', 'email_exist');
INSERT INTO internationalization (word_en, word_ru, group_enum, key_word) VALUES ('Reset password. Please enter a registered user email.', '', 'messages', 'reset_password');
INSERT INTO internationalization (word_en, word_ru, group_enum, key_word) VALUES ('Reset password  Your link has expired. Please reset your password to receive a new link.', '', 'messages', 'expired_restore_link');