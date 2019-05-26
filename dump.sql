-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le :  Dim 26 mai 2019 à 18:44
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `kotlin-cms`
--

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE `articles` (
  `id` int(11) NOT NULL,
  `text` text NOT NULL,
  `title` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `articles`
--

INSERT INTO `articles` (`id`, `text`, `title`) VALUES
(4, 'On a beaucoup parlé du gobelet Starbucks oublié sur une table de banquet de Winterfell, ou de la bouteille d\'eau de Samwell Tarly durant la scène du conseil, mais il y a d\'autres faux raccords dans la huitième et dernière saison de Game of Thrones. Michel & Michel ont analysé à la loupe les six derniers des soixante-treize épisodes de la série HBO, et vous dévoile leurs trouvailles dans le player ci-dessus. Valar Gaffis !\r\n\r\nEt pour les vrais fans... Tous les morts depuis le début de la série [SPOILERS]', 'Game of Thrones saison 8 : les gaffes, erreurs et faux raccords'),
(5, 'Game of Thrones, c’est terminé. Définitivement terminé. HBO a en effet diffusé dimanche soir dernier l’ultime épisode de la série. Un épisode qui n’a pas manqué de provoquer de nombreuses réactions chez les internautes, cela va de soi. Et certaines valent bien entendu le détour.\r\n\r\n\r\n \r\nSi vous n’avez pas encore eu l’occasion de regarder le final de Game of Thrones, ou si vous avez encore quelques épisodes de retard, alors il sera préférable de ne pas poursuivre la lecture de cet article.\r\n\r\nGame of Thrones saison 8\r\nCapture OCS\r\n\r\nLes lignes suivantes sont en effet susceptibles de contenir de nombreux spoilers.\r\n\r\nGame of Thrones : un final en demie teinte\r\nHB a donc diffusé dimanche soir le tout dernier épisode de la série, une série qui a marqué au fer rouge l’histoire de la chaîne, bien sûr, mais aussi de la télévision en général.\r\n\r\nPour ce final, les showrunneurs ont choisi de ne pas faire dans le spectacle. L’épisode était en effet beaucoup plus mesuré que le précédent et il se focalisait ainsi davantage sur le dénouement de l’intrigue initiée en 2010.\r\n\r\nReste que D.B. Weiss et David Benioff nous ont tout de même préparé quelques surprises. Entre la mort précipitée et un peu bizarre de Daenerys, le coup de colère de Drogon et le couronnement de Bran, on peut même dire que le duo a tout mis en œuvre pour tenter de surprendre les fans.\r\n\r\nAvec, malheureusement, des résultats très mitigés. Ce chapitre final n’a effectivement pas fait l’unanimité chez les fans et beaucoup ont ainsi été agacés du traitement infligé à la Mère des Dragons. Après avoir traversé une bonne partie du monde, libéré plusieurs villes et contribué à défaire l’armée des Marcheurs Blancs, on aurait en effet pu lui imaginer un avenir un peu plus radieux.\r\n\r\nMais ce n’est visiblement pas le point qui a le plus fait débat. Les internautes ont aussi été nombreux à réagir au couronnement de Bran, un Bran qui avait pourtant déclaré à plusieurs reprises qu’il ne souhaitait pas monter sur un quelconque trône et qu’il n’avait plus rien d’un Stark.\r\n\r\nEt bien sûr, c’est sans parler de Tyrion qui est devenu Main pour la troisième fois consécutive… avec trois rois différents.\r\n\r\nLe bon côté des choses, c’est que les réactions des internautes ont été nombreuses, elles, et même souvent drôles.', 'GAME OF THRONES : LES MEILLEURES RÉACTIONS DES INTERNAUTES APRÈS L’ÉPISODE FINAL');

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `isAdmin`) VALUES
(1, 'username', 'password', 1),
(2, 'username2', 'password2', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `articles`
--
ALTER TABLE `articles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
