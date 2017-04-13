-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 13 Avril 2017 à 23:09
-- Version du serveur :  10.1.19-MariaDB
-- Version de PHP :  5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sr03`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `text` text NOT NULL,
  `date_article` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `command`
--

CREATE TABLE `command` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_command` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `command`
--

INSERT INTO `command` (`id`, `id_user`, `date_command`) VALUES
(1, 1, '2017-04-13 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `console`
--

CREATE TABLE `console` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `console`
--

INSERT INTO `console` (`id`, `name`) VALUES
(1, 'NINTENDO DS');

-- --------------------------------------------------------

--
-- Structure de la table `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `genre`
--

INSERT INTO `genre` (`id`, `name`) VALUES
(4, 'Action'),
(2, 'FPS'),
(3, 'Réflexion'),
(1, 'RPG');

-- --------------------------------------------------------

--
-- Structure de la table `linecommand`
--

CREATE TABLE `linecommand` (
  `id_command` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `linecommand`
--

INSERT INTO `linecommand` (`id_command`, `id_product`, `quantity`) VALUES
(1, 2, 55);

-- --------------------------------------------------------

--
-- Structure de la table `picture`
--

CREATE TABLE `picture` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `id_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `date_release` date NOT NULL,
  `id_videogame` int(11) NOT NULL,
  `id_console` int(11) NOT NULL,
  `price` double NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `product`
--

INSERT INTO `product` (`date_release`, `id_videogame`, `id_console`, `price`, `id`) VALUES
('2017-04-12', 2, 1, 0, 1),
('2017-04-18', 1, 1, 0, 2);

-- --------------------------------------------------------

--
-- Structure de la table `properties`
--

CREATE TABLE `properties` (
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `publisher`
--

CREATE TABLE `publisher` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `publisher`
--

INSERT INTO `publisher` (`id`, `name`) VALUES
(1, 'NINTENDO');

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `mark` int(255) NOT NULL,
  `text` text NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tag`
--

CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tagarticle`
--

CREATE TABLE `tagarticle` (
  `id_tag` int(11) NOT NULL,
  `id_article` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tagvideogame`
--

CREATE TABLE `tagvideogame` (
  `id_tag` int(11) NOT NULL,
  `id_videogame` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `username`, `mail`, `firstname`, `surname`, `password`) VALUES
(1, 'konamdav', 'david.konam@utc.fr', 'david', 'konam', 'test'),
(10, '22', 'test', 'dv', 'konam', '123'),
(11, 'dk', 'mail', 'dav', 'konam', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `userrole`
--

CREATE TABLE `userrole` (
  `id_user` int(11) NOT NULL,
  `role` varchar(255) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `userrole`
--

INSERT INTO `userrole` (`id_user`, `role`, `id`) VALUES
(1, 'ADMIN', 0);

-- --------------------------------------------------------

--
-- Structure de la table `videogame`
--

CREATE TABLE `videogame` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `id_publisher` int(11) NOT NULL,
  `id_genre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `videogame`
--

INSERT INTO `videogame` (`id`, `name`, `id_publisher`, `id_genre`) VALUES
(1, 'POKEMON', 1, 1),
(2, 'Kingdom DZB', 1, 2);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `command`
--
ALTER TABLE `command`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_user_2` (`id_user`),
  ADD KEY `date_order` (`date_command`);

--
-- Index pour la table `console`
--
ALTER TABLE `console`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `linecommand`
--
ALTER TABLE `linecommand`
  ADD PRIMARY KEY (`id_command`,`id_product`),
  ADD KEY `id_product` (`id_product`);

--
-- Index pour la table `picture`
--
ALTER TABLE `picture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_product` (`id_product`);

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_videogame` (`id_videogame`,`id_console`),
  ADD UNIQUE KEY `id_videogame_2` (`id_videogame`,`id_console`),
  ADD KEY `id_console` (`id_console`);

--
-- Index pour la table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `id_user` (`id_user`,`id_product`),
  ADD KEY `id_product` (`id_product`);

--
-- Index pour la table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `tagarticle`
--
ALTER TABLE `tagarticle`
  ADD PRIMARY KEY (`id_tag`,`id_article`),
  ADD KEY `id_videogame` (`id_article`);

--
-- Index pour la table `tagvideogame`
--
ALTER TABLE `tagvideogame`
  ADD PRIMARY KEY (`id_tag`,`id_videogame`),
  ADD KEY `id_videogame` (`id_videogame`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- Index pour la table `userrole`
--
ALTER TABLE `userrole`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_user` (`id_user`),
  ADD UNIQUE KEY `id_role` (`role`);

--
-- Index pour la table `videogame`
--
ALTER TABLE `videogame`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `id_publisher` (`id_publisher`),
  ADD KEY `id_genre` (`id_genre`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `command`
--
ALTER TABLE `command`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `console`
--
ALTER TABLE `console`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `picture`
--
ALTER TABLE `picture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `publisher`
--
ALTER TABLE `publisher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT pour la table `videogame`
--
ALTER TABLE `videogame`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `command`
--
ALTER TABLE `command`
  ADD CONSTRAINT `command_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `linecommand`
--
ALTER TABLE `linecommand`
  ADD CONSTRAINT `linecommand_ibfk_1` FOREIGN KEY (`id_command`) REFERENCES `command` (`id`),
  ADD CONSTRAINT `linecommand_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`);

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_videogame`) REFERENCES `videogame` (`id`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`id_console`) REFERENCES `console` (`id`);

--
-- Contraintes pour la table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `tagarticle`
--
ALTER TABLE `tagarticle`
  ADD CONSTRAINT `tagarticle_ibfk_1` FOREIGN KEY (`id_article`) REFERENCES `article` (`id`),
  ADD CONSTRAINT `tagarticle_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id`);

--
-- Contraintes pour la table `tagvideogame`
--
ALTER TABLE `tagvideogame`
  ADD CONSTRAINT `TagVideoGame_ibfk_1` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id`),
  ADD CONSTRAINT `TagVideoGame_ibfk_2` FOREIGN KEY (`id_videogame`) REFERENCES `videogame` (`id`);

--
-- Contraintes pour la table `userrole`
--
ALTER TABLE `userrole`
  ADD CONSTRAINT `userrole_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `videogame`
--
ALTER TABLE `videogame`
  ADD CONSTRAINT `videogame_ibfk_1` FOREIGN KEY (`id_publisher`) REFERENCES `publisher` (`id`),
  ADD CONSTRAINT `videogame_ibfk_2` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
