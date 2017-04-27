-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 20 Avril 2017 à 20:51
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
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `linecommand`
--

INSERT INTO `linecommand` (`id_command`, `id_product`, `quantity`, `price`) VALUES
(1, 2, 55, 0);

-- --------------------------------------------------------

--
-- Structure de la table `pegi_classification`
--

CREATE TABLE `pegi_classification` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

--
-- Contenu de la table `picture`
--

INSERT INTO `picture` (`id`, `name`, `img`, `id_product`) VALUES
(1, 'miniature dbz', 'test', 2);

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
('2017-04-12', 2, 1, 7, 1),
('2017-04-18', 1, 1, 11.5, 2);

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
(6, '1'),
(5, 'goubin&co'),
(2, 'konami'),
(1, 'NINTENDO'),
(4, 'rockstar'),
(3, 'square enix');

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `note` int(255) NOT NULL,
  `content` text NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `review`
--

INSERT INTO `review` (`id`, `title`, `note`, `content`, `id_user`, `id_product`) VALUES
(1, 'mon avis', 5, 'génial', 1, 2),
(2, 'omg', 4, 'omg', 10, 2);

-- --------------------------------------------------------

--
-- Structure de la table `tag`
--

CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tag`
--

INSERT INTO `tag` (`id`, `name`) VALUES
(2, '#doctor'),
(1, '#pokemon');

-- --------------------------------------------------------

--
-- Structure de la table `tag_videogame`
--

CREATE TABLE `tag_videogame` (
  `id_tag` int(11) NOT NULL,
  `id_videogame` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tag_videogame`
--

INSERT INTO `tag_videogame` (`id_tag`, `id_videogame`) VALUES
(1, 1),
(1, 2);

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
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `username`, `mail`, `firstname`, `surname`, `password`, `role`) VALUES
(1, 'konamdav', 'david.konam@utc.fr', 'david', 'konam', 'test', 'USER'),
(10, '22', 'test', 'dv', 'konam', '123', 'USER'),
(11, 'dk', 'mail', 'dav', 'konam', 'test', 'USER');

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

-- --------------------------------------------------------

--
-- Structure de la table `videogame_genre`
--

CREATE TABLE `videogame_genre` (
  `id_videogame` int(11) NOT NULL,
  `id_genre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `videogame_genre`
--

INSERT INTO `videogame_genre` (`id_videogame`, `id_genre`) VALUES
(1, 1),
(1, 3),
(2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `videogame_pegi_classification`
--

CREATE TABLE `videogame_pegi_classification` (
  `id_videogame` int(11) NOT NULL,
  `id_pegi_classification` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

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
-- Index pour la table `pegi_classification`
--
ALTER TABLE `pegi_classification`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

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
  ADD UNIQUE KEY `name` (`title`),
  ADD UNIQUE KEY `id_user` (`id_user`,`id_product`),
  ADD KEY `id_product` (`id_product`);

--
-- Index pour la table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `tag_videogame`
--
ALTER TABLE `tag_videogame`
  ADD PRIMARY KEY (`id_tag`,`id_videogame`),
  ADD KEY `id_videogame` (`id_videogame`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- Index pour la table `videogame`
--
ALTER TABLE `videogame`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `id_publisher` (`id_publisher`),
  ADD KEY `id_genre` (`id_genre`);

--
-- Index pour la table `videogame_genre`
--
ALTER TABLE `videogame_genre`
  ADD PRIMARY KEY (`id_videogame`,`id_genre`),
  ADD KEY `id_genre` (`id_genre`);

--
-- Index pour la table `videogame_pegi_classification`
--
ALTER TABLE `videogame_pegi_classification`
  ADD PRIMARY KEY (`id_pegi_classification`,`id_videogame`),
  ADD KEY `id_videogame` (`id_videogame`);

--
-- AUTO_INCREMENT pour les tables exportées
--

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
-- AUTO_INCREMENT pour la table `pegi_classification`
--
ALTER TABLE `pegi_classification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `picture`
--
ALTER TABLE `picture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `publisher`
--
ALTER TABLE `publisher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
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
-- Contraintes pour la table `picture`
--
ALTER TABLE `picture`
  ADD CONSTRAINT `picture_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`);

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
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`);

--
-- Contraintes pour la table `tag_videogame`
--
ALTER TABLE `tag_videogame`
  ADD CONSTRAINT `TagVideoGame_ibfk_1` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id`),
  ADD CONSTRAINT `TagVideoGame_ibfk_2` FOREIGN KEY (`id_videogame`) REFERENCES `videogame` (`id`);

--
-- Contraintes pour la table `videogame`
--
ALTER TABLE `videogame`
  ADD CONSTRAINT `videogame_ibfk_1` FOREIGN KEY (`id_publisher`) REFERENCES `publisher` (`id`),
  ADD CONSTRAINT `videogame_ibfk_2` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`id`);

--
-- Contraintes pour la table `videogame_genre`
--
ALTER TABLE `videogame_genre`
  ADD CONSTRAINT `videogame_genre_ibfk_1` FOREIGN KEY (`id_videogame`) REFERENCES `videogame` (`id`),
  ADD CONSTRAINT `videogame_genre_ibfk_2` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`id`);

--
-- Contraintes pour la table `videogame_pegi_classification`
--
ALTER TABLE `videogame_pegi_classification`
  ADD CONSTRAINT `videogame_pegi_classification_ibfk_1` FOREIGN KEY (`id_videogame`) REFERENCES `videogame` (`id`),
  ADD CONSTRAINT `videogame_pegi_classification_ibfk_2` FOREIGN KEY (`id_pegi_classification`) REFERENCES `pegi_classification` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
