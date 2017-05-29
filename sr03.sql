-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 29 Mai 2017 à 17:45
-- Version du serveur :  10.1.19-MariaDB
-- Version de PHP :  5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Structure de la table `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(11) NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Structure de la table `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_length` text COLLATE utf8_bin,
  `col_collation` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) COLLATE utf8_bin DEFAULT '',
  `col_default` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Structure de la table `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `settings_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Contenu de la table `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{"snap_to_grid":"on","angular_direct":"direct","relation_lines":"true","full_screen":"on"}');

-- --------------------------------------------------------

--
-- Structure de la table `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `export_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `template_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `template_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Structure de la table `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Structure de la table `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Structure de la table `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Contenu de la table `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{"db":"sr03","table":"picture"},{"db":"sr03","table":"tag"},{"db":"sr03","table":"tag_videogame"},{"db":"sr03","table":"videogame"},{"db":"sr03","table":"pegi_classification"},{"db":"sr03","table":"videogame_pegi_classification"},{"db":"sr03","table":"Review"},{"db":"sr03","table":"review"},{"db":"sr03","table":"linecommand"},{"db":"sr03","table":"product"}]');

-- --------------------------------------------------------

--
-- Structure de la table `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

--
-- Contenu de la table `pma__relation`
--

INSERT INTO `pma__relation` (`master_db`, `master_table`, `master_field`, `foreign_db`, `foreign_table`, `foreign_field`) VALUES
('sr03', 'UserOrder', 'id_user', 'sr03', 'user', 'id');

-- --------------------------------------------------------

--
-- Structure de la table `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float UNSIGNED NOT NULL DEFAULT '0',
  `y` float UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Structure de la table `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Contenu de la table `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2017-03-29 19:24:25', '{"lang":"fr","collation_connection":"utf8mb4_unicode_ci"}');

-- --------------------------------------------------------

--
-- Structure de la table `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Structure de la table `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Index pour les tables exportées
--

--
-- Index pour la table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Index pour la table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Index pour la table `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Index pour la table `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Index pour la table `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Index pour la table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Index pour la table `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Index pour la table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Index pour la table `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Index pour la table `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Index pour la table `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Index pour la table `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Index pour la table `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Index pour la table `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;--
-- Base de données :  `sr03`
--
CREATE DATABASE IF NOT EXISTS `sr03` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sr03`;

-- --------------------------------------------------------

--
-- Structure de la table `command`
--

CREATE TABLE `command` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_command` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `command`
--

INSERT INTO `command` (`id`, `id_user`, `date_command`) VALUES
(8, 1, '2017-05-04 13:21:16'),
(9, 1, '2017-05-05 13:32:40'),
(10, 1, '2017-05-05 14:26:53'),
(11, 1, '2017-05-27 09:36:46'),
(12, 1, '2017-05-27 09:39:36'),
(13, 1, '2017-05-27 11:51:40'),
(14, 1, '2017-05-27 12:11:19'),
(15, 1, '2017-05-27 12:16:31'),
(16, 1, '2017-05-29 16:52:26');

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
(5, 'GAME BOY ADVANCE'),
(6, 'GAME BOY ADVANCE SP'),
(10, 'GAME CUBE'),
(12, 'N64'),
(4, 'NINTENDO DS'),
(11, 'PC'),
(8, 'PLAYSTATION'),
(7, 'PSP'),
(3, 'PSP VITA'),
(9, 'XBOX');

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
(2, 'ACTION'),
(10, 'ARCADE'),
(1, 'AVENTURE'),
(6, 'COURSE'),
(5, 'EDUCATION'),
(4, 'FPS'),
(7, 'MYSTERE'),
(9, 'REFLEXION'),
(8, 'RPG'),
(3, 'TEST');

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
(8, 4, 2, 15),
(9, 4, 10, 15),
(10, 4, 5, 15),
(12, 4, 3, 15),
(13, 4, 100, 15),
(14, 4, 1, 15),
(15, 4, 1, 15),
(15, 6, 1, 12.3),
(16, 5, 1, 17.5);

-- --------------------------------------------------------

--
-- Structure de la table `pegi_classification`
--

CREATE TABLE `pegi_classification` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pegi_classification`
--

INSERT INTO `pegi_classification` (`id`, `name`, `img`) VALUES
(1, '3 ans +', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/PEGI_n_3.svg/2000px-PEGI_n_3.svg.png'),
(2, '7 ans +', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/PEGI_7.svg/2000px-PEGI_7.svg.png'),
(3, '12 ans +', 'https://superhotgame.com/wp-content/uploads/2016/04/pegi-12.jpg'),
(4, '16 ans +', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/PEGI_16.svg/426px-PEGI_16.svg.png'),
(5, '18 ans +', 'http://www.mcvuk.com/cimages/6d8cf260e1bbdc5eaf586fd26b95ab23.jpg'),
(7, 'DROGUES', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/PEGI_Violence.svg/600px-PEGI_Violence.svg.png'),
(8, 'ARGENT', 'https://www.pixelsprite.fr/wp-content/uploads/2014/11/PEGI_gambling.svg_.png'),
(9, '21 ans +', 'http://www.vapoteurs.net/wp-content/uploads/2017/03/Rating_21.png');

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
(1, 'pokemon1', 'https://d.ibtimes.co.uk/en/full/1493849/pokemon-sun-moon-3ds.png?w=400', 4),
(2, 'pokemon2', 'http://nintendoenthusiast.com/wp-content/uploads/2016/11/sun-and-moon2.jpg', 4),
(3, 'pokemon3', 'https://images.eurogamer.net/2016/usgamer/pokemon-sun-moon-review-spot-1.png', 4),
(4, 'pokemon4', 'https://cdn.vox-cdn.com/uploads/chorus_asset/file/7473349/clothing_store_F.0.jpg', 4),
(5, 'pokemon5', 'http://geeko.lesoir.be/wp-content/uploads/sites/58/2016/12/pok1.jpg', 4),
(6, 'pokemon5', 'http://geeko.lesoir.be/wp-content/uploads/sites/58/2016/12/pok1.jpg', 5),
(7, 'pokemon5', 'http://geeko.lesoir.be/wp-content/uploads/sites/58/2016/12/pok1.jpg', 6),
(8, 'pokemon5', 'http://geeko.lesoir.be/wp-content/uploads/sites/58/2016/12/pok1.jpg', 7),
(9, 'pokemon5', 'http://geeko.lesoir.be/wp-content/uploads/sites/58/2016/12/pok1.jpg', 8),
(10, 'pokemon3', 'https://images.eurogamer.net/2016/usgamer/pokemon-sun-moon-review-spot-1.png', 8),
(11, 'pokemon2', 'http://nintendoenthusiast.com/wp-content/uploads/2016/11/sun-and-moon2.jpg', 12),
(13, 'ae71b165-5032-499a-a22c-1a25b7f73abb', 'http://www.pokemontrash.com/images//rubis-saphir/pension.jpg', 13),
(19, '585cdc92-a3a1-4bf7-92bc-acb407a62fdd', 'http://www.pokepedia.fr/images/thumb/3/3f/Pok%C3%A9mon_Saphir_Recto.png/200px-Pok%C3%A9mon_Saphir_Recto.png', 13),
(20, '4f4f3cd6-b8ea-4439-86b2-4ab732dc7209', 'http://www.manga-news.com/public/images/jeuxvideo/screenshots/pokemon-saphir-gba-1.jpg', 13);

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `date_release` int(11) NOT NULL,
  `id_videogame` int(11) NOT NULL,
  `id_console` int(11) NOT NULL,
  `price` double NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `product`
--

INSERT INTO `product` (`date_release`, `id_videogame`, `id_console`, `price`, `id`) VALUES
(1995, 1, 3, 15, 4),
(1996, 3, 11, 17.5, 5),
(2001, 2, 6, 12.3, 6),
(215, 6, 8, 40.2, 7),
(2004, 5, 3, 5, 8),
(1960, 1, 12, 15.3, 12),
(2006, 1, 5, 400, 13);

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
(6, 'BANDAI GAMES'),
(1, 'KONAMI'),
(7, 'MOJANG'),
(3, 'ROCKSTAR'),
(5, 'SONIC TEAM'),
(2, 'SQUARE ENIX'),
(4, 'UBISOFT');

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `note` int(255) NOT NULL,
  `content` text NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `review`
--

INSERT INTO `review` (`id`, `note`, `content`, `id_user`, `id_product`, `title`) VALUES
(5, 4, 'Pas terrible. Les graphismes ne sont pas le point fort du jeu.', 2, 4, ''),
(6, 9, 'J''ai adoré', 3, 4, ''),
(10, 3, 'content', 1, 12, 'super jeu'),
(11, 1, 'content', 1, 4, 'super jeu');

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
(1, 'tag1'),
(2, 'tag2'),
(3, 'tag3'),
(4, 'tag4'),
(5, 'tag5'),
(642, 'tagsr03');

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
(1, 3),
(1, 4),
(642, 1);

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
(1, 'konamdav', 'mail.david.konam@gmail.com', 'David', 'KONAM', 'sr03testsr03', 'ADMIN'),
(2, 'client', 'client@gmail.com', 'Benjamin', 'KONAM', 'sr03testsr03', 'USER'),
(3, 'sbrooks', 'sbrooks@gmail.com', 'Sienna', 'BROOKS', 'sr03testsr03', 'USER'),
(4, 'client-test45', 'mailte8st', 'david', 'konam', 'c3IwM3Rlc3RzcjAz', 'USER'),
(5, 'dkk', 'dddokdojd@dk', 'dddd', 'ddddd', 'ZGRrb2Rrb2Rrb2Rr', 'USER'),
(7, 'dk4k', 'dddokdojd@dk454', 'dddd', 'ddddd', 'ZGRrb2Rrb2Rrb2Rr', 'USER'),
(8, 'dhdhd', 'dddokdojd@dk454455', 'dddd', 'ddddd', 'ZGRrb2Rrb2Rrb2Rr', 'USER'),
(9, 'ddkdkdkdkdkd', 'oo@kkj', 'ooo', 'opooko', 'YWxhaWE=', 'USER'),
(10, 'antoine', 'what@cominghome', 'dont', 'know', 'ZmRw', 'USER'),
(11, 'test-cors', 'dk@cors', 'cors', 'saire', 'Y29ycw==', 'USER'),
(12, 'holmes', 'shh@w', 'sh', 'shh', 'Nzg5', 'USER');

-- --------------------------------------------------------

--
-- Structure de la table `videogame`
--

CREATE TABLE `videogame` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `id_publisher` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `videogame`
--

INSERT INTO `videogame` (`id`, `name`, `id_publisher`) VALUES
(1, 'Pokemon Saphir', 1),
(2, 'Mario Kart', 2),
(3, 'Doctor Who : Time space', 5),
(4, 'GTA 5', 1),
(5, 'Assassin''s creed Unity', 4),
(6, 'Assassin''s creed Redemption', 3),
(7, 'Pokemon Rubis', 6);

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
(1, 2),
(1, 10),
(2, 2),
(2, 5),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 1),
(7, 2);

-- --------------------------------------------------------

--
-- Structure de la table `videogame_pegi_classification`
--

CREATE TABLE `videogame_pegi_classification` (
  `id_videogame` int(11) NOT NULL,
  `id_pegi_classification` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `videogame_pegi_classification`
--

INSERT INTO `videogame_pegi_classification` (`id_videogame`, `id_pegi_classification`) VALUES
(3, 1),
(4, 1),
(1, 2),
(1, 7);

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
  ADD UNIQUE KEY `id_user` (`id_user`,`id_product`),
  ADD UNIQUE KEY `title` (`id_user`,`id_product`),
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
  ADD UNIQUE KEY `mail` (`mail`),
  ADD UNIQUE KEY `mail_2` (`mail`),
  ADD UNIQUE KEY `mail_3` (`mail`);

--
-- Index pour la table `videogame`
--
ALTER TABLE `videogame`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `id_publisher` (`id_publisher`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT pour la table `console`
--
ALTER TABLE `console`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `pegi_classification`
--
ALTER TABLE `pegi_classification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `picture`
--
ALTER TABLE `picture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `publisher`
--
ALTER TABLE `publisher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT pour la table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=643;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `videogame`
--
ALTER TABLE `videogame`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
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
  ADD CONSTRAINT `videogame_ibfk_1` FOREIGN KEY (`id_publisher`) REFERENCES `publisher` (`id`);

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
