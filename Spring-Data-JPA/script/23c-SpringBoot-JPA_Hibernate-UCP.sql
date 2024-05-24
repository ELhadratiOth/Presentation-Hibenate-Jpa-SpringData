CREATE TABLE T_Users (
    idUser              NUMBER PRIMARY KEY,
    login               VARCHAR2(20) NOT NULL,
    password            VARCHAR2(20) NOT NULL
);
INSERT INTO T_Users (idUser, login, password) VALUES (1, 'Anderson', 'Neo');
INSERT INTO T_Users (idUser, login, password) VALUES (2, 'Skywalker', 'Luke');
INSERT INTO T_Users (idUser, login, password) VALUES (3, 'Plissken', 'Snake');
INSERT INTO T_Users (idUser, login, password) VALUES (4, 'Ripley', 'Ellen');
INSERT INTO T_Users (idUser, login, password) VALUES (5, 'Bond', 'James');

CREATE TABLE T_Roles (
    idRole       NUMBER PRIMARY KEY,
    roleName     VARCHAR2(20) NOT NULL
);
INSERT INTO T_Roles (idRole, roleName) VALUES (1, 'client');
INSERT INTO T_Roles (idRole, roleName) VALUES (2, 'admin');
INSERT INTO T_Roles (idRole, roleName) VALUES (3, 'stockManager');

CREATE TABLE T_Users_Roles_Associations (
    idUser   NUMBER NOT NULL,
    idRole   NUMBER NOT NULL,
    FOREIGN KEY (idUser) REFERENCES T_Users(idUser),
    FOREIGN KEY (idRole) REFERENCES T_Roles(idRole)
);
INSERT INTO T_Users_Roles_Associations (idUser, idRole) VALUES (1, 2);
INSERT INTO T_Users_Roles_Associations (idUser, idRole) VALUES (1, 3);
INSERT INTO T_Users_Roles_Associations (idUser, idRole) VALUES (4, 1);
INSERT INTO T_Users_Roles_Associations (idUser, idRole) VALUES (5, 1);

CREATE TABLE T_Commands (
    idCommand       NUMBER PRIMARY KEY,
    idUser          NUMBER NOT NULL,
    commandDate     DATE DEFAULT SYSDATE NOT NULL,
    FOREIGN KEY (idUser) REFERENCES T_Users(idUser)
);
INSERT INTO T_Commands (idCommand, idUser) VALUES (1, 1);
INSERT INTO T_Commands (idCommand, idUser) VALUES (2, 2);
INSERT INTO T_Commands (idCommand, idUser) VALUES (3, 1);
