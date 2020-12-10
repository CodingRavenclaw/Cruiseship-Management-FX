-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2020 at 08:07 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `greenpioneer`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblcabins`
--

CREATE TABLE `tblcabins` (
  `cabinNo` int(3) NOT NULL,
  `type` varchar(4) NOT NULL,
  `deck` varchar(2) NOT NULL,
  `max_passenger_capacity` tinyint(1) NOT NULL,
  `size_in_sq_m` double NOT NULL,
  `no_of_beds` tinyint(1) NOT NULL,
  `no_of_toilets` tinyint(1) NOT NULL,
  `price_per_person_in_EUR` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblcabins`
--

INSERT INTO `tblcabins` (`cabinNo`, `type`, `deck`, `max_passenger_capacity`, `size_in_sq_m`, `no_of_beds`, `no_of_toilets`, `price_per_person_in_EUR`) VALUES
(1, 'LS', 'B', 4, 40, 2, 2, 1380),
(2, 'LS', 'B', 4, 40, 2, 2, 1380),
(3, 'S', 'B', 3, 30, 2, 1, 1073),
(4, 'S', 'B', 3, 30, 2, 1, 1073),
(5, 'B', 'B', 3, 16, 2, 1, 911),
(6, 'B', 'B', 3, 16, 2, 1, 911),
(7, 'B', 'B', 3, 16, 2, 1, 911),
(8, 'B', 'B', 3, 16, 2, 1, 911),
(9, 'B', 'B', 3, 16, 2, 1, 911),
(10, 'B', 'B', 2, 14, 1, 1, 875),
(11, 'B', 'B', 2, 14, 1, 1, 875),
(12, 'B', 'B', 2, 14, 1, 1, 875),
(14, 'B', 'B', 2, 14, 1, 1, 875),
(15, 'B', 'B', 2, 14, 1, 1, 875),
(16, 'B', 'B', 2, 14, 1, 1, 875),
(17, 'B', 'B', 2, 14, 1, 1, 875),
(18, 'B', 'B', 2, 14, 1, 1, 875),
(19, 'B', 'B', 2, 14, 1, 1, 875),
(20, 'B', 'B', 2, 14, 1, 1, 875),
(21, 'B', 'B', 2, 14, 1, 1, 875),
(22, 'B', 'B', 2, 14, 1, 1, 875),
(23, 'B', 'B', 2, 14, 1, 1, 875),
(24, 'B', 'B', 2, 14, 1, 1, 875),
(25, 'B', 'B', 2, 14, 1, 1, 875),
(26, 'B', 'B', 2, 14, 1, 1, 875),
(27, 'B', 'B', 2, 14, 1, 1, 875),
(28, 'B', 'B', 2, 14, 1, 1, 875),
(29, 'B', 'B', 2, 14, 1, 1, 875),
(30, 'B', 'B', 2, 14, 1, 1, 875),
(31, 'B', 'B', 2, 14, 1, 1, 875),
(32, 'B', 'B', 2, 14, 1, 1, 875),
(33, 'B', 'B', 2, 14, 1, 1, 875),
(34, 'B', 'B', 2, 14, 1, 1, 875),
(35, 'B', 'B', 2, 14, 1, 1, 875),
(36, 'B', 'B', 2, 14, 1, 1, 875),
(37, 'B', 'B', 2, 14, 1, 1, 875),
(38, 'B', 'B', 2, 14, 1, 1, 875),
(39, 'B', 'B', 2, 14, 1, 1, 875),
(40, 'B', 'B', 2, 14, 1, 1, 875),
(41, 'O', 'C', 3, 13, 2, 1, 768),
(42, 'O', 'C', 3, 13, 2, 1, 768),
(43, 'O', 'C', 3, 13, 2, 1, 768),
(44, 'O', 'C', 2, 11, 1, 1, 712),
(45, 'O', 'C', 2, 11, 1, 1, 712),
(46, 'O', 'C', 2, 11, 1, 1, 712),
(47, 'O', 'C', 2, 11, 1, 1, 712),
(48, 'O', 'C', 2, 11, 1, 1, 712),
(49, 'O', 'C', 2, 11, 1, 1, 712),
(50, 'O', 'C', 2, 11, 1, 1, 712),
(51, 'O', 'C', 2, 11, 1, 1, 712),
(52, 'O', 'C', 2, 11, 1, 1, 712),
(53, 'O', 'C', 2, 11, 1, 1, 712),
(54, 'O', 'C', 2, 11, 1, 1, 712),
(55, 'O', 'C', 2, 11, 1, 1, 712),
(56, 'O', 'C', 2, 11, 1, 1, 712),
(57, 'O', 'C', 2, 11, 1, 1, 712),
(58, 'O', 'C', 2, 11, 1, 1, 712),
(59, 'O', 'C', 2, 11, 1, 1, 712),
(60, 'O', 'C', 2, 11, 1, 1, 712),
(61, 'O', 'C', 2, 11, 1, 1, 712),
(62, 'O', 'C', 2, 11, 1, 1, 712),
(63, 'O', 'C', 2, 11, 1, 1, 712),
(64, 'O', 'C', 2, 11, 1, 1, 712),
(65, 'O', 'C', 2, 11, 1, 1, 712),
(66, 'O', 'C', 2, 11, 1, 1, 712),
(67, 'O', 'C', 2, 11, 1, 1, 712),
(68, 'O', 'C', 2, 11, 1, 1, 712),
(69, 'O', 'C', 2, 11, 1, 1, 712),
(70, 'O', 'C', 2, 11, 1, 1, 712),
(71, 'I', 'C', 3, 10, 2, 1, 630),
(72, 'I', 'C', 3, 10, 2, 1, 630),
(73, 'I', 'C', 3, 10, 2, 1, 630),
(74, 'I', 'C', 3, 10, 2, 1, 630),
(75, 'I', 'C', 3, 10, 2, 1, 630),
(76, 'I', 'C', 3, 10, 2, 1, 630),
(77, 'I', 'C', 3, 10, 2, 1, 630),
(78, 'I', 'C', 2, 10, 1, 1, 512),
(79, 'I', 'C', 2, 10, 1, 1, 512),
(80, 'I', 'C', 2, 10, 1, 1, 512),
(81, 'I', 'C', 2, 10, 1, 1, 512),
(82, 'I', 'C', 2, 10, 1, 1, 512),
(83, 'I', 'C', 2, 10, 1, 1, 512),
(84, 'I', 'C', 2, 10, 1, 1, 512),
(85, 'I', 'C', 2, 10, 1, 1, 512),
(86, 'I', 'C', 2, 10, 1, 1, 512),
(87, 'I', 'C', 2, 10, 1, 1, 512),
(88, 'I', 'C', 2, 10, 1, 1, 512),
(89, 'I', 'C', 2, 10, 1, 1, 512),
(90, 'I', 'D', 2, 10, 1, 1, 512),
(91, 'I', 'D', 2, 10, 1, 1, 512),
(92, 'I', 'D', 2, 10, 1, 1, 512),
(93, 'I', 'D', 2, 10, 1, 1, 512),
(94, 'I', 'D', 2, 10, 1, 1, 512),
(95, 'I', 'D', 2, 10, 1, 1, 512),
(96, 'I', 'D', 2, 10, 1, 1, 512),
(97, 'I', 'D', 2, 10, 1, 1, 512),
(98, 'I', 'D', 2, 10, 1, 1, 512),
(99, 'I', 'D', 2, 10, 1, 1, 512),
(100, 'I', 'D', 2, 10, 1, 1, 512),
(101, 'I', 'D', 2, 10, 1, 1, 512),
(102, 'I', 'D', 2, 10, 1, 1, 512),
(103, 'I', 'D', 2, 10, 1, 1, 512),
(104, 'I', 'D', 2, 10, 1, 1, 512),
(105, 'I', 'D', 2, 10, 1, 1, 512),
(106, 'I', 'D', 2, 10, 1, 1, 512),
(107, 'I', 'D', 2, 10, 1, 1, 512),
(108, 'I', 'D', 2, 10, 1, 1, 512),
(109, 'I', 'D', 2, 10, 1, 1, 512),
(110, 'I', 'D', 2, 10, 1, 1, 512),
(111, 'I', 'D', 2, 10, 1, 1, 512);

-- --------------------------------------------------------

--
-- Table structure for table `tblcabintypes`
--

CREATE TABLE `tblcabintypes` (
  `type` varchar(4) NOT NULL,
  `description` varchar(32) NOT NULL,
  `equipment` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblcabintypes`
--

INSERT INTO `tblcabintypes` (`type`, `description`, `equipment`) VALUES
('B', 'Balcony cabin', '1 double bed or 2 single beds, TV, DVD and BlueRay, telephone, safe, hairdryer, marmor bathroom with shower, WC, direct access to the deck'),
('I', 'Inside cabin', '1 double bed, (1 single bed), TV, DVD and BlueRay, telephone, safe, hairdryer, marmor bathroom with shower, WC'),
('LS', 'Luxury suite', '2 double beds, seperate living room, TV, DVD and BlueRay, telephone, safe, hairdryer, minibar, marmor bathroom with shower and whirlpool, room service'),
('O', 'Outside cabin', '1 double bed or 2 single beds, TV, DVD and BlueRay, telephone, safe, hairdryer, marmor bathroom with shower, WC'),
('S', 'Suite', '1 double bed, 1 single bed, living area, TV, DVD and BlueRay, telephone, safe, hairdryer, minibar, marmor bathroom with shower and whirlpool, room service');

-- --------------------------------------------------------

--
-- Table structure for table `tblcountries`
--

CREATE TABLE `tblcountries` (
  `code` varchar(4) NOT NULL,
  `en` varchar(128) NOT NULL,
  `de` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblcountries`
--

INSERT INTO `tblcountries` (`code`, `en`, `de`) VALUES
('AD', 'Andorra', 'Andorra'),
('AE', 'United Arab Emirates', 'Vereinigte Arabische Emirate'),
('AF', 'Afghanistan', 'Afghanistan'),
('AG', 'Antigua and Barbuda', 'Antigua und Barbuda'),
('AI', 'Anguilla', 'Anguilla'),
('AL', 'Albania', 'Albanien'),
('AM', 'Armenia', 'Armenien'),
('AN', 'Netherlands Antilles', 'Niederländische Antillen'),
('AO', 'Angola', 'Angola'),
('AQ', 'Antarctica', 'Antarktis'),
('AR', 'Argentina', 'Argentinien'),
('AS', 'American Samoa', 'Amerikanisch-Samoa'),
('AT', 'Austria', 'Österreich'),
('AU', 'Australia', 'Australien'),
('AW', 'Aruba', 'Aruba'),
('AX', 'Aland Islands', 'Åland'),
('AZ', 'Azerbaijan', 'Aserbaidschan'),
('BA', 'Bosnia and Herzegovina', 'Bosnien und Herzegowina'),
('BB', 'Barbados', 'Barbados'),
('BD', 'Bangladesh', 'Bangladesch'),
('BE', 'Belgium', 'Belgien'),
('BF', 'Burkina Faso', 'Burkina Faso'),
('BG', 'Bulgaria', 'Bulgarien'),
('BH', 'Bahrain', 'Bahrain'),
('BI', 'Burundi', 'Burundi'),
('BJ', 'Benin', 'Benin'),
('BL', 'Saint Barthelemy !Saint Barthélemy', 'Saint-Barthélemy'),
('BM', 'Bermuda', 'Bermuda'),
('BN', 'Brunei', 'Brunei Darussalam'),
('BO', 'Bolivia', 'Bolivien'),
('BQ', 'Bonaire', 'Sint Eustatius and Saba'),
('BR', 'Brazil', 'Brasilien'),
('BS', 'Bahamas', 'Bahamas'),
('BT', 'Bhutan', 'Bhutan'),
('BV', 'Bouvet Island', 'Bouvetinsel'),
('BW', 'Botswana', 'Botswana'),
('BY', 'Belarus', 'Belarus (Weißrussland)'),
('BZ', 'Belize', 'Belize'),
('CA', 'Canada', 'Kanada'),
('CC', 'Cocos (Keeling) Islands', 'Kokosinseln (Keelinginseln)'),
('CD', 'Congo (Kinshasa)', 'Kongo'),
('CF', 'Central African Republic', 'Zentralafrikanische Republik'),
('CG', 'Congo (Brazzaville)', 'Republik Kongo'),
('CH', 'Switzerland', 'Schweiz'),
('CI', 'Ivory Coast', 'Elfenbeinküste'),
('CK', 'Cook Islands', 'Cookinseln'),
('CL', 'Chile', 'Chile'),
('CM', 'Cameroon', 'Kamerun'),
('CN', 'China', 'China'),
('CO', 'Colombia', 'Kolumbien'),
('CR', 'Costa Rica', 'Costa Rica'),
('CU', 'Cuba', 'Kuba'),
('CV', 'Cape Verde', 'Kap Verde'),
('CW', 'Curacao !Curaçao', 'Curaçao'),
('CX', 'Christmas Island', 'Weihnachtsinsel'),
('CY', 'Cyprus', 'Zypern'),
('CZ', 'Czech Republic', 'Tschechische Republik'),
('DE', 'Germany', 'Deutschland'),
('DJ', 'Djibouti', 'Dschibuti'),
('DK', 'Denmark', 'Dänemark'),
('DM', 'Dominica', 'Dominica'),
('DO', 'Dominican Republic', 'Dominikanische Republik'),
('DZ', 'Algeria', 'Algerien'),
('EC', 'Ecuador', 'Ecuador'),
('EE', 'Estonia', 'Estland (Reval)'),
('EG', 'Egypt', 'Ägypten'),
('EH', 'Western Sahara', 'Westsahara'),
('ER', 'Eritrea', 'Eritrea'),
('ES', 'Spain', 'Spanien'),
('ET', 'Ethiopia', 'Äthiopien'),
('FI', 'Finland', 'Finnland'),
('FJ', 'Fiji', 'Fidschi'),
('FK', 'Falkland Islands', 'Falklandinseln (Malwinen)'),
('FM', 'Micronesia', 'Mikronesien'),
('FO', 'Faroe Islands', 'Färöer'),
('FR', 'France', 'Frankreich'),
('GA', 'Gabon', 'Gabun'),
('GB', 'United Kingdom', 'Großbritannien und Nordirland'),
('GD', 'Grenada', 'Grenada'),
('GE', 'Georgia', 'Georgien'),
('GF', 'French Guiana', 'Französisch-Guayana'),
('GG', 'Guernsey', 'Guernsey (Kanalinsel)'),
('GH', 'Ghana', 'Ghana'),
('GI', 'Gibraltar', 'Gibraltar'),
('GL', 'Greenland', 'Grönland'),
('GM', 'Gambia', 'Gambia'),
('GN', 'Guinea', 'Guinea'),
('GP', 'Guadeloupe', 'Guadeloupe'),
('GQ', 'Equatorial Guinea', 'Äquatorialguinea'),
('GR', 'Greece', 'Griechenland'),
('GS', 'South Georgia and the South Sandwich Islands', 'Südgeorgien und die Südl. Sandwichinseln'),
('GT', 'Guatemala', 'Guatemala'),
('GU', 'Guam', 'Guam'),
('GW', 'Guinea-Bissau', 'Guinea-Bissau'),
('GY', 'Guyana', 'Guyana'),
('HK', 'Hong Kong S.A.R.', 'China'),
('HM', 'Heard Island and McDonald Islands', 'Heard- und McDonald-Inseln'),
('HN', 'Honduras', 'Honduras'),
('HR', 'Croatia', 'Kroatien'),
('HT', 'Haiti', 'Haiti'),
('HU', 'Hungary', 'Ungarn'),
('ID', 'Indonesia', 'Indonesien'),
('IE', 'Ireland', 'Irland'),
('IL', 'Israel', 'Israel'),
('IM', 'Isle of Man', 'Insel Man'),
('IN', 'India', 'Indien'),
('IO', 'British Indian Ocean Territory', 'Britisches Territorium im Indischen Ozean'),
('IQ', 'Iraq', 'Irak'),
('IR', 'Iran', 'Iran'),
('IS', 'Iceland', 'Island'),
('IT', 'Italy', 'Italien'),
('JE', 'Jersey', 'Jersey (Kanalinsel)'),
('JM', 'Jamaica', 'Jamaika'),
('JO', 'Jordan', 'Jordanien'),
('JP', 'Japan', 'Japan'),
('KE', 'Kenya', 'Kenia'),
('KG', 'Kyrgyzstan', 'Kirgisistan'),
('KH', 'Cambodia', 'Kambodscha'),
('KI', 'Kiribati', 'Kiribati'),
('KM', 'Comoros', 'Komoren'),
('KN', 'Saint Kitts and Nevis', 'St. Kitts und Nevis'),
('KP', 'North Korea', 'Nordkorea'),
('KR', 'South Korea', 'Südkorea'),
('KW', 'Kuwait', 'Kuwait'),
('KY', 'Cayman Islands', 'Kaimaninseln'),
('KZ', 'Kazakhstan', 'Kasachstan'),
('LA', 'Laos', 'Laos'),
('LB', 'Lebanon', 'Libanon'),
('LC', 'Saint Lucia', 'St. Lucia'),
('LI', 'Liechtenstein', 'Liechtenstein'),
('LK', 'Sri Lanka', 'Sri Lanka'),
('LR', 'Liberia', 'Liberia'),
('LS', 'Lesotho', 'Lesotho'),
('LT', 'Lithuania', 'Litauen'),
('LU', 'Luxembourg', 'Luxemburg'),
('LV', 'Latvia', 'Lettland'),
('LY', 'Libya', 'Libyen'),
('MA', 'Morocco', 'Marokko'),
('MC', 'Monaco', 'Monaco'),
('MD', 'Moldova', 'Moldawien'),
('ME', 'Montenegro', 'Montenegro'),
('MF', 'Saint Martin (French part)', 'Saint-Martin (franz. Teil)'),
('MG', 'Madagascar', 'Madagaskar'),
('MH', 'Marshall Islands', 'Marshallinseln'),
('MK', 'Macedonia', 'Mazedonien'),
('ML', 'Mali', 'Mali'),
('MM', 'Myanmar', 'Myanmar (Burma)'),
('MN', 'Mongolia', 'Mongolei'),
('MO', 'Macao S.A.R.', ' China'),
('MP', 'Northern Mariana Islands', 'Nördliche Marianen'),
('MQ', 'Martinique', 'Martinique'),
('MR', 'Mauritania', 'Mauretanien'),
('MS', 'Montserrat', 'Montserrat'),
('MT', 'Malta', 'Malta'),
('MU', 'Mauritius', 'Mauritius'),
('MV', 'Maldives', 'Malediven'),
('MW', 'Malawi', 'Malawi'),
('MX', 'Mexico', 'Mexiko'),
('MY', 'Malaysia', 'Malaysia'),
('MZ', 'Mozambique', 'Mosambik'),
('NA', 'Namibia', 'Namibia'),
('NC', 'New Caledonia', 'Neukaledonien'),
('NE', 'Niger', 'Niger'),
('NF', 'Norfolk Island', 'Norfolkinsel'),
('NG', 'Nigeria', 'Nigeria'),
('NI', 'Nicaragua', 'Nicaragua'),
('NL', 'Netherlands', 'Niederlande'),
('NO', 'Norway', 'Norwegen'),
('NP', 'Nepal', 'Nepal'),
('NR', 'Nauru', 'Nauru'),
('NU', 'Niue', 'Niue'),
('NZ', 'New Zealand', 'Neuseeland'),
('OM', 'Oman', 'Oman'),
('PA', 'Panama', 'Panama'),
('PE', 'Peru', 'Peru'),
('PF', 'French Polynesia', 'Französisch-Polynesien'),
('PG', 'Papua New Guinea', 'Papua-Neuguinea'),
('PH', 'Philippines', 'Philippinen'),
('PK', 'Pakistan', 'Pakistan'),
('PL', 'Poland', 'Polen'),
('PM', 'Saint Pierre and Miquelon', 'St. Pierre und Miquelon'),
('PN', 'Pitcairn', 'Pitcairninseln'),
('PR', 'Puerto Rico', 'Puerto Rico'),
('PS', 'Palestine', 'Palästina'),
('PT', 'Portugal', 'Portugal'),
('PW', 'Palau', 'Palau'),
('PY', 'Paraguay', 'Paraguay'),
('QA', 'Qatar', 'Katar'),
('RE', 'Reunion', 'Réunion'),
('RO', 'Romania', 'Rumänien'),
('RS', 'Serbia', 'Serbien'),
('RU', 'Russia', 'Russische Föderation'),
('RW', 'Rwanda', 'Ruanda'),
('SA', 'Saudi Arabia', 'Saudi-Arabien'),
('SB', 'Solomon Islands', 'Salomonen'),
('SC', 'Seychelles', 'Seychellen'),
('SD', 'Sudan', 'Sudan'),
('SE', 'Sweden', 'Schweden'),
('SG', 'Singapore', 'Singapur'),
('SH', 'Saint Helena', 'St. Helena'),
('SI', 'Slovenia', 'Slowenien'),
('SJ', 'Svalbard and Jan Mayen', 'Svalbard und Jan Mayen'),
('SK', 'Slovakia', 'Slowakei'),
('SL', 'Sierra Leone', 'Sierra Leone'),
('SM', 'San Marino', 'San Marino'),
('SN', 'Senegal', 'Senegal'),
('SO', 'Somalia', 'Somalia'),
('SR', 'Suriname', 'Suriname'),
('SS', 'South Sudan', 'Südsudan'),
('ST', 'Sao Tome and Principe', 'São Tomé und Príncipe'),
('SV', 'El Salvador', 'El Salvador'),
('SX', 'Sint Maarten (Dutch part)', 'Sint Maarten (niederl. Teil)'),
('SY', 'Syria', 'Syrien'),
('SZ', 'Swaziland', 'Swasiland'),
('TC', 'Turks and Caicos Islands', 'Turks- und Caicosinseln'),
('TD', 'Chad', 'Tschad'),
('TF', 'French Southern Territories', 'Französische Süd- und Antarktisgebiete'),
('TG', 'Togo', 'Togo'),
('TH', 'Thailand', 'Thailand'),
('TJ', 'Tajikistan', 'Tadschikistan'),
('TK', 'Tokelau', 'Tokelau'),
('TL', 'East Timor', 'Timor-Leste'),
('TM', 'Turkmenistan', 'Turkmenistan'),
('TN', 'Tunisia', 'Tunesien'),
('TO', 'Tonga', 'Tonga'),
('TR', 'Turkey', 'Türkei'),
('TT', 'Trinidad and Tobago', 'Trinidad und Tobago'),
('TV', 'Tuvalu', 'Tuvalu'),
('TW', 'Taiwan', 'Taiwan'),
('TZ', 'Tanzania', 'Tansania'),
('UA', 'Ukraine', 'Ukraine'),
('UG', 'Uganda', 'Uganda'),
('UM', 'United States Minor Outlying Islands', 'Amerikanisch-Ozeanien'),
('US', 'United States', 'Vereinigte Staaten von Amerika'),
('UY', 'Uruguay', 'Uruguay'),
('UZ', 'Uzbekistan', 'Usbekistan'),
('VA', 'Vatican', 'Vatikanstadt'),
('VC', 'Saint Vincent and the Grenadines', 'St. Vincent und die Grenadinen'),
('VE', 'Venezuela', 'Venezuela'),
('VG', 'British Virgin Islands', 'Britische Jungferninseln'),
('VI', 'U.S. Virgin Islands', 'Amerikanische Jungferninseln'),
('VN', 'Vietnam', 'Vietnam'),
('VU', 'Vanuatu', 'Vanuatu'),
('WF', 'Wallis and Futuna', 'Wallis und Futuna'),
('WS', 'Samoa', 'Samoa'),
('YE', 'Yemen', 'Jemen'),
('YT', 'Mayotte', 'Mayotte'),
('ZA', 'South Africa', 'Südafrika'),
('ZM', 'Zambia', 'Sambia'),
('ZW', 'Zimbabwe', 'Simbabwe');

-- --------------------------------------------------------

--
-- Table structure for table `tblcrewpersons`
--

CREATE TABLE `tblcrewpersons` (
  `crewpersonNo` int(3) NOT NULL,
  `first_name` varchar(48) NOT NULL,
  `last_name` varchar(48) NOT NULL,
  `date_of_birth` date NOT NULL,
  `date_of_employment` date NOT NULL,
  `gender` char(1) NOT NULL,
  `nationality` varchar(4) NOT NULL,
  `position` varchar(8) NOT NULL,
  `salary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblcrewpersons`
--

INSERT INTO `tblcrewpersons` (`crewpersonNo`, `first_name`, `last_name`, `date_of_birth`, `date_of_employment`, `gender`, `nationality`, `position`, `salary`) VALUES
(1, 'Círdan', 'Mithlond', '1962-10-19', '2013-05-30', 'm', 'GB', 'CPT', 9577.32),
(2, 'Polly', 'Jungnickel', '1964-12-27', '2013-05-31', 'f', 'US', 'CE', 9521.55),
(3, 'Beatrice', 'Hollmann', '1967-09-27', '2013-06-06', 'f', 'US', 'SA', 3762),
(4, 'Emina', 'Çagatay', '1968-11-27', '2013-06-11', 'f', 'TR', 'SA', 3762),
(5, 'Linda', 'Pleckaitis', '1969-06-16', '2013-06-13', 'f', 'AU', 'SA', 3762),
(6, 'Scholastika', 'Isacsson', '1969-11-26', '2013-06-14', 'f', 'SE', 'SA', 3762),
(7, 'Tyler', 'Rodenburg', '1973-10-09', '2013-06-17', 'm', 'DE', 'Wtr', 2996.28),
(8, 'Marthe', 'Steinbach', '1974-06-06', '2013-06-18', 'f', 'DE', 'MedO', 7596.53),
(9, 'Christie', 'Ayçiçek', '1976-01-13', '2013-06-19', 'f', 'TR', 'SA', 3762),
(10, 'Michael', 'Steinberg', '1976-06-25', '2013-06-20', 'm', 'DE', '2Off', 6025.72),
(11, 'Gereon', 'Irenäus', '1978-02-21', '2013-06-24', 'm', 'DK', 'SA', 3762),
(12, 'Gennadi', 'Garbaravicius', '1979-06-25', '2013-06-26', 'm', 'DK', 'SA', 3762),
(13, 'Elle', 'Westermann', '1980-06-13', '2013-06-27', 'f', 'DE', 'Wtr', 2996.28),
(14, 'Wolfhart', 'Camilleri', '1981-08-12', '2013-07-01', 'm', 'IT', 'SA', 3762),
(15, 'Dieter', 'Womelsdorf', '1982-06-05', '2013-07-03', 'm', 'DE', 'Ck', 3893.86),
(16, 'Giselher', 'Reinhard', '1983-02-18', '2013-07-05', 'm', 'DE', '3E', 5463.1),
(17, 'Alexandro', 'Márquez', '1983-10-31', '2013-07-08', 'm', 'ES', 'SA', 3762),
(18, 'Remy', 'Schweitzer', '1984-01-24', '2013-07-15', 'm', 'DE', 'Brkpr', 3184.92),
(19, 'Taskin', 'Ouédraogo', '1987-07-25', '2013-07-18', 'm', 'MA', 'Stwrd', 3165.86),
(20, 'Jodok', 'Marshall', '1990-06-22', '2013-07-19', 'm', 'US', 'SA', 3762),
(21, 'Halise', 'Theodore', '1990-08-02', '2013-07-23', 'm', 'US', 'Stwrd', 3165.86),
(22, 'Hanni', 'Griffiths', '1991-12-23', '2013-07-24', 'f', 'GB', 'SA', 3762),
(23, 'Anaďs', 'Poppinga', '1993-11-04', '2013-07-25', 'f', 'MX', 'SA', 3762),
(24, 'Miranda', 'Kaufmann', '1994-10-30', '2013-07-26', 'f', 'DE', 'MedO', 7596.53),
(25, 'Hilal', 'Ottosson', '1999-12-11', '2013-07-29', 'f', 'GB', '4Off', 5273.49),
(26, 'Jurate', 'Lamanauskas', '1961-11-04', '2013-05-30', 'm', 'IN', 'Wtr', 2996.28),
(27, 'Sarah', 'Buchholz', '1965-01-29', '2013-05-31', 'f', 'DE', 'SA', 3762),
(28, 'Ethel', 'Beuschel', '1966-08-09', '2013-06-03', 'f', 'DE', 'Stwrd', 3165.86),
(29, 'Mabel', 'Gökdemir', '1967-01-20', '2013-06-04', 'f', 'TR', 'SA', 3762),
(30, 'Perihan', 'Rößler', '1968-04-16', '2013-06-05', 'm', 'DE', 'Ck', 3893.86),
(31, 'Piero', 'Kubiliunas', '1968-10-24', '2013-06-07', 'm', 'IT', 'Stwrd', 3165.86),
(32, 'Richie', 'Cherubino', '1969-12-15', '2013-06-10', 'm', 'IT', 'E', 3975),
(33, 'Masahiro', 'O’Donoghue', '1971-05-21', '2013-06-11', 'm', 'IE', 'Stwrd', 3165.86),
(34, 'Jürg', 'Ó Cuív', '1971-11-04', '2013-06-12', 'm', 'IE', 'E', 3975),
(35, 'Crispin', 'Mĺrtensson', '1974-09-30', '2013-06-14', 'm', 'GB', 'Stwrd', 3165.86),
(36, 'Diarmuid', 'Hangarter', '1977-03-04', '2013-06-17', 'm', 'US', 'E', 3975),
(37, 'Branko', 'Smollich', '1978-06-10', '2013-06-18', 'm', 'DE', 'Wtr', 2996.28),
(38, 'Johann', 'Davidsson', '1979-10-27', '2013-06-21', 'm', 'AU', 'SA', 3762),
(39, 'Martino', 'Constantinescu', '1980-10-10', '2013-06-25', 'm', 'ES', 'Stwrd', 3165.86),
(40, 'Ĺge', 'Subacius', '1989-10-06', '2013-06-28', 'm', 'DK', 'Off', 4616.91),
(41, 'Jaroslava', 'Cansel', '1989-10-14', '2013-07-01', 'f', 'BG', '3Off', 5692.34),
(42, 'Nadeschda', 'Toraman', '1994-06-03', '2013-07-02', 'f', 'RU', '2E', 5836.82),
(43, 'Anneliese', 'Türkoglu', '1994-10-22', '2013-07-08', 'f', 'TR', '1Off', 6393.53),
(44, 'Petronella', 'Abulafia', '1995-01-05', '2013-07-09', 'f', 'MA', 'Ck', 3893.86),
(45, 'Gundelinde', 'Fredriksen', '1996-10-23', '2013-07-11', 'f', 'MX', 'Brkpr', 3184.92),
(46, 'Catriona', 'Christoff', '1997-05-04', '2013-07-12', 'f', 'SE', 'Wtr', 2996.28),
(47, 'Nikoleta', 'Schweminski', '1998-07-15', '2013-07-17', 'f', 'SE', 'E', 3975),
(48, 'Marijona', 'King', '1998-08-14', '2013-07-24', 'f', 'US', 'Wtr', 2996.28),
(49, 'Jeanette', 'Soler', '2000-02-19', '2013-07-30', 'f', 'FR', 'Off', 4616.91),
(50, 'Violette', 'Aguirre', '2000-09-05', '2013-07-31', 'f', 'FR', 'SA', 3762),
(51, 'Eilfried', 'Rieser', '1961-02-15', '2013-05-31', 'm', 'DE', 'E', 3975),
(52, 'Takeo', 'Yamamura', '1961-10-19', '2013-06-03', 'm', 'JP', 'Wtr', 2996.28),
(53, 'Zvonimir', 'Vardar', '1963-04-07', '2013-06-04', 'm', 'RU', 'SA', 3762),
(54, 'Tasdemir', 'Daudert', '1963-08-08', '2013-06-06', 'f', 'MA', 'Wtr', 2996.28),
(55, 'Innozenz', 'Coleman', '1967-11-26', '2013-06-07', 'm', 'MX', 'E', 3975),
(56, 'Gultekin', 'Riedel', '1967-12-01', '2013-06-10', 'm', 'AU', 'SA', 3762),
(57, 'Žilvinas', 'Urbonas', '1968-08-23', '2013-06-11', 'm', 'CY', 'Stwrd', 3165.86),
(58, 'Zvonimir', 'Erkan', '1969-05-18', '2013-06-13', 'm', 'TR', 'Stwrd', 3165.86),
(59, 'Hansjörg', 'Müller', '1969-12-24', '2013-06-17', 'm', 'DE', '1E', 6264.36),
(60, 'Aurelijus', 'Ergert', '1971-11-02', '2013-06-18', 'm', 'DE', 'Brkpr', 3184.92),
(61, 'Brodie', 'Gamble', '1972-05-25', '2013-06-24', 'm', 'GB', 'Stwrd', 3165.86),
(62, 'Jayce', 'Beaumont', '1972-05-27', '2013-06-27', 'm', 'GB', 'Ck', 3893.86),
(63, 'Carlo', 'Coates', '1973-01-15', '2013-07-03', 'm', 'FR', 'Stwrd', 3165.86),
(64, 'Emile', 'Mullen', '1973-11-17', '2013-07-05', 'f', 'IT', 'Wtr', 2996.28),
(65, 'Omer', 'Sharples', '1978-01-28', '2013-07-08', 'm', 'US', '5Off', 4881.37),
(66, 'Bhavik', 'Mccallum', '1979-08-31', '2013-07-09', 'm', 'TR', 'E', 3975),
(67, 'Alessandro', 'Gillespie', '1981-03-06', '2013-07-10', 'm', 'IT', 'Wtr', 2996.28),
(68, 'Charmaine', 'Robles', '1985-01-02', '2013-07-11', 'f', 'FR', '4E', 5159.67),
(69, 'Martine', 'Reynolds', '1985-03-20', '2013-07-12', 'f', 'GB', 'Wtr', 2996.28),
(70, 'Sarah', 'Bradshaw', '1986-02-01', '2013-07-17', 'f', 'US', 'E', 3975),
(71, 'Ernie', 'Shaffer', '1987-12-13', '2013-07-18', 'm', 'DE', 'Off', 4616.91),
(72, 'Molly', 'Woodcock', '1989-10-20', '2013-07-22', 'f', 'AU', 'Wtr', 2996.28),
(73, 'Yvie', 'Sosa', '1993-01-11', '2013-07-24', 'f', 'GB', 'Off', 4616.91),
(74, 'Alexandria', 'Shelton', '1998-06-13', '2013-07-30', 'f', 'GB', 'Wtr', 2996.28),
(75, 'Kiera', 'O\'Reilly', '1998-08-20', '2013-07-31', 'f', 'IE', 'RO', 4842.64),
(76, 'Nisha', 'Sheppard', '1961-09-14', '2013-05-30', 'f', 'IE', 'Wtr', 2996.28),
(77, 'Gabrielius', 'Rutledge', '1961-10-19', '2013-05-31', 'm', 'IT', 'Wtr', 2996.28),
(78, 'Eadie', 'Gibbons', '1962-06-12', '2013-06-03', 'f', 'DE', 'Off', 4616.91),
(79, 'Joseff', 'Anthony', '1963-01-28', '2013-06-04', 'm', 'DE', 'Stwrd', 3165.86),
(80, 'Imaan', 'Terrell', '1966-10-23', '2013-06-11', 'm', 'AU', 'Brkpr', 3184.92),
(81, 'Jimi', 'Lancaster', '1969-09-13', '2013-06-12', 'm', 'GB', 'Stwrd', 3165.86),
(82, 'Jude', 'Wolf', '1971-03-24', '2013-06-13', 'f', 'DE', 'RO', 4842.64),
(83, 'Shiv', 'Simpson', '1971-08-30', '2013-06-14', 'f', 'GB', 'Stwrd', 3165.86),
(84, 'Sheena', 'Byers', '1971-11-24', '2013-06-18', 'f', 'MA', 'E', 3975),
(85, 'Caris', 'Bridges', '1975-08-09', '2013-06-25', 'm', 'ES', 'CSA', 4438.72),
(86, 'Brian', 'Huang', '1976-12-22', '2013-06-26', 'm', 'VN', 'E', 3975),
(87, 'Stefano', 'Mclean', '1977-07-07', '2013-07-02', 'm', 'IE', 'Stwrd', 3165.86),
(88, 'Sansa', 'Stark', '1978-06-13', '2013-07-03', 'f', 'NO', 'SA', 3762),
(89, 'Margie', 'Quintana', '1979-07-17', '2013-07-04', 'f', 'CA', 'Ck', 3893.86),
(90, 'Sultan', 'Lindsey', '1980-02-02', '2013-07-08', 'm', 'CA', 'Stwrd', 3165.86),
(91, 'Alfie-Lee', 'Caldwell', '1980-04-17', '2013-07-09', 'f', 'US', 'Wtr', 2996.28),
(92, 'Adam', 'Small', '1980-10-01', '2013-07-10', 'm', 'GB', 'Ck', 3893.86),
(93, 'Chaya', 'Swift', '1984-01-09', '2013-07-11', 'f', 'CH', 'Lman', 2843.96),
(94, 'Lukasz', 'Tate', '1984-07-07', '2013-07-16', 'm', 'US', 'Stwrd', 3165.86),
(95, 'Marnie', 'Ross', '1985-06-07', '2013-07-19', 'f', 'DE', 'Lman', 2843.96),
(96, 'Lucca', 'Firth', '1989-03-19', '2013-07-22', 'm', 'DE', 'Wtr', 2996.28),
(97, 'Krishan', 'Day', '1992-08-12', '2013-07-25', 'm', 'CH', 'Stwrd', 3165.86),
(98, 'Portia', 'Bailey', '1992-09-23', '2013-07-26', 'f', 'ES', 'Stwrd', 3165.86),
(99, 'Martyna', 'O\'Quinn', '1999-11-16', '2013-07-29', 'f', 'IE', 'Lman', 2843.96),
(100, 'Harlen', 'Tyler', '2000-09-28', '2013-07-31', 'f', 'US', 'Wtr', 2996.28),
(101, 'Terry', 'Bass', '1960-05-02', '2013-05-30', 'm', 'US', 'Stwrd', 3165.86),
(102, 'Wasim', 'Esquivel', '1963-02-27', '2013-05-31', 'm', 'MX', 'Ck', 3893.86),
(103, 'Xander', 'Sutherland', '1966-05-03', '2013-06-03', 'm', 'US', 'CK', 3893.86),
(104, 'Josef', 'Wills', '1967-02-13', '2013-06-04', 'm', 'GB', 'Lman', 2843.96),
(105, 'Liyah', 'Huerta', '1968-02-24', '2013-06-11', 'f', 'MA', 'Stwrd', 3165.86),
(106, 'Emir', 'Conroy', '1969-05-12', '2013-06-12', 'm', 'AU', 'Wtr', 2996.28),
(107, 'Leja', 'Ellwood', '1970-04-25', '2013-06-13', 'f', 'US', 'Stwrd', 3165.86),
(108, 'Beauden', 'Kouma', '1970-11-23', '2013-06-14', 'm', 'FR', 'Wtr', 2996.28),
(109, 'Myla', 'Person', '1971-05-19', '2013-06-18', 'f', 'AU', 'Wtr', 2996.28),
(110, 'Jaspal', 'Shepherd', '1971-05-28', '2013-06-25', 'm', 'NZ', 'Brkpr', 3184.92);

-- --------------------------------------------------------

--
-- Table structure for table `tblcrewpositions`
--

CREATE TABLE `tblcrewpositions` (
  `position` varchar(8) NOT NULL,
  `description` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblcrewpositions`
--

INSERT INTO `tblcrewpositions` (`position`, `description`) VALUES
('1E', '1st engineer'),
('1Off', '1st officer'),
('2E', '2nd engineer'),
('2Off', '2nd officer'),
('3E', '3rd engineer'),
('3Off', '3rd officer'),
('4E', '4th engineer'),
('4Off', '4th officer'),
('5Off', '5th officer'),
('Brkpr', 'Barkeeper'),
('CE', 'Chief engineer'),
('Ck', 'Cook'),
('CPT', 'Captain'),
('CSA', 'Chief sailor'),
('E', 'Engineer'),
('Lman', 'Laundry man'),
('MedO', 'Medical officer'),
('Off', 'Officer'),
('RO', 'Radio officer'),
('SA', 'Sailor'),
('Stwrd', 'Steward'),
('Wtr', 'Waiter');

-- --------------------------------------------------------

--
-- Table structure for table `tblcruises`
--

CREATE TABLE `tblcruises` (
  `cruiseNo` int(6) NOT NULL,
  `passengerNo` int(6) NOT NULL,
  `cabinNo` int(3) NOT NULL,
  `origin_port_LOCODE` varchar(8) NOT NULL,
  `destination_port_LOCODE` varchar(8) NOT NULL,
  `cruise_start` date NOT NULL,
  `cruise_end` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblcruises`
--

INSERT INTO `tblcruises` (`cruiseNo`, `passengerNo`, `cabinNo`, `origin_port_LOCODE`, `destination_port_LOCODE`, `cruise_start`, `cruise_end`) VALUES
(1, 1, 1, 'AUSYD', 'AUSYD', '2020-07-03', '2020-06-25'),
(1, 12, 21, 'DEHAM', 'USBOS', '2013-06-01', '2013-06-08'),
(1, 13, 42, 'DEHAM', 'USBOS', '2013-06-01', '2013-06-08'),
(1, 27, 90, 'DEHAM', 'USBOS', '2013-06-01', '2013-06-08'),
(1, 28, 89, 'DEHAM', 'USBOS', '2013-06-01', '2013-06-08'),
(1, 55, 9, 'DEHAM', 'USBOS', '2013-06-01', '2013-06-08'),
(1, 67, 16, 'DEHAM', 'USBOS', '2013-06-01', '2013-06-08'),
(1, 70, 56, 'DEHAM', 'USBOS', '2013-06-01', '2013-06-08'),
(2, 6, 49, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(2, 11, 12, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(2, 26, 41, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(2, 43, 94, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(2, 44, 110, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(2, 63, 108, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(2, 66, 47, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(2, 78, 40, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(2, 79, 46, 'DEHAM', 'MAAGA', '2013-06-19', '2013-06-26'),
(3, 4, 11, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 5, 37, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 8, 31, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 14, 74, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 15, 100, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 16, 72, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 17, 59, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 19, 51, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 30, 104, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 37, 1, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 39, 3, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 42, 6, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 47, 45, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 50, 74, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 52, 57, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 58, 48, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 59, 81, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(3, 72, 74, 'DEHAM', 'GBLON', '2013-07-07', '2013-07-10'),
(4, 2, 43, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 3, 32, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 9, 34, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 18, 107, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 23, 24, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 24, 29, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 29, 82, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 32, 53, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 36, 78, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 40, 30, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 45, 61, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 46, 93, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 49, 65, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 51, 4, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 53, 111, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 54, 28, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 57, 25, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 60, 103, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 62, 85, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 64, 23, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 65, 99, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 68, 27, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 71, 86, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 74, 4, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(4, 80, 50, 'DEHAM', 'JPYOK', '2013-08-04', '2013-09-03'),
(5, 3, 102, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 7, 64, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 11, 80, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 12, 8, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 13, 7, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 14, 5, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 22, 69, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 23, 77, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 24, 101, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 25, 26, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 28, 88, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 31, 66, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 33, 12, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 39, 106, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 40, 39, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 42, 17, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 44, 55, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 48, 58, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 49, 92, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 50, 60, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 51, 73, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 52, 35, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 59, 52, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 67, 36, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 68, 15, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 73, 22, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 76, 95, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(5, 78, 71, 'DEHAM', 'TRIZM', '2013-09-27', '2013-10-22'),
(6, 1, 3, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 2, 70, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 8, 61, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 9, 76, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 10, 90, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 15, 65, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 16, 9, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 19, 43, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 20, 6, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 27, 68, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 30, 105, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 32, 106, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 34, 40, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 35, 31, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 37, 11, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 43, 16, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 47, 26, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 57, 110, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 62, 22, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 63, 29, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 66, 75, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 70, 10, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 72, 77, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 74, 56, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(6, 77, 97, 'DEHAM', 'ZACPT', '2013-11-26', '2013-12-31'),
(7, 3, 2, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 5, 84, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 6, 94, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 8, 39, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 10, 50, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 14, 44, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 16, 18, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 17, 72, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 21, 52, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 22, 17, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 23, 91, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 25, 100, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 30, 48, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 31, 79, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 35, 7, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 37, 34, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 38, 63, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 39, 59, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 41, 33, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 46, 96, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 53, 109, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 55, 27, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 63, 103, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 66, 38, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 68, 99, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 70, 37, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 72, 102, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 74, 89, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(7, 79, 15, 'DEHAM', 'USNYC', '2014-02-18', '2014-02-25'),
(8, 4, 19, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 7, 108, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 12, 45, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 13, 24, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 18, 5, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 19, 57, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 20, 86, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 24, 4, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 27, 101, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 28, 85, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 29, 43, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 32, 104, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 34, 43, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 36, 95, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 40, 71, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 42, 43, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 45, 64, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 47, 93, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 48, 92, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 49, 55, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 51, 73, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 52, 98, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 54, 111, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 57, 1, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 58, 49, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 60, 23, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 61, 30, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 62, 47, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 64, 21, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 65, 12, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 67, 107, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 69, 58, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 71, 80, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 76, 66, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 78, 35, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15'),
(8, 80, 81, 'DEHAM', 'ESLPA', '2014-03-08', '2014-03-15');

-- --------------------------------------------------------

--
-- Table structure for table `tblpassengers`
--

CREATE TABLE `tblpassengers` (
  `passengerNo` int(8) NOT NULL,
  `first_name` varchar(48) NOT NULL,
  `last_name` varchar(48) NOT NULL,
  `gender` char(1) NOT NULL,
  `date_of_birth` date NOT NULL,
  `nationality` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblpassengers`
--

INSERT INTO `tblpassengers` (`passengerNo`, `first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`) VALUES
(1, 'Mason', 'Fleming', 'm', '1950-10-11', 'US'),
(2, 'Elias', 'Leach', 'm', '1955-07-24', 'US'),
(3, 'Callam', 'Howells', 'm', '1957-10-02', 'GB'),
(4, 'Leopold', 'Decker', 'm', '1958-08-27', 'DE'),
(5, 'Amber-Rose', 'Mathis', 'f', '1959-09-27', 'DE'),
(6, 'Sue', 'Barnett', 'f', '1960-01-03', 'GB'),
(7, 'Haseeb', 'Pearson', 'm', '1963-08-25', 'GB'),
(8, 'Jason', 'Velazquez', 'm', '1964-12-17', 'ES'),
(9, 'Marvin', 'Caldwell', 'm', '1968-12-20', 'GB'),
(10, 'Daniel', 'Bogen', 'm', '1969-05-07', 'DE'),
(11, 'Bertram', 'Hogan', 'm', '1969-09-03', 'DE'),
(12, 'Wiktoria', 'Alexander', 'f', '1972-09-25', 'DE'),
(13, 'Emma-Louise', 'Coffey', 'f', '1974-02-10', 'IE'),
(14, 'Blair', 'Mcbride', 'm', '1975-01-19', 'IE'),
(15, 'Gordon', 'Freeman', 'm', '1981-10-20', 'US'),
(16, 'Adeline', 'Hawkins', 'f', '1982-08-26', 'US'),
(17, 'Fatima', 'Castro', 'f', '1985-03-29', 'IT'),
(18, 'Mehdi', 'Johnston', 'm', '1985-10-15', 'MA'),
(19, 'Ernst', 'Immel', 'm', '1986-08-24', 'DE'),
(20, 'Sasha', 'Harding', 'm', '1986-10-31', 'DE'),
(21, 'Sarah', 'McGonagall', 'f', '1987-06-09', 'GB'),
(22, 'Shanay', 'Dupont', 'f', '1988-08-23', 'US'),
(23, 'Michelle', 'King', 'f', '1991-06-08', 'US'),
(24, 'Garrett', 'Barker', 'm', '1991-12-11', 'GB'),
(25, 'Hamid', 'Eastwood', 'm', '1994-03-16', 'GB'),
(26, 'Paloma', 'Singh', 'f', '1950-03-31', 'IT'),
(27, 'Phillipa', 'Underwood', 'f', '1953-10-28', 'GB'),
(28, 'Marguerite', 'Delacruz', 'f', '1954-05-01', 'ES'),
(29, 'Zak', 'Greer', 'm', '1955-01-31', 'MX'),
(30, 'Fay', 'Bowers', 'f', '1955-06-01', 'MX'),
(31, 'Avery', 'Clements', 'f', '1955-10-31', 'IE'),
(32, 'Dominykas', 'Contreras', 'm', '1957-12-19', 'PE'),
(33, 'Fatimah', 'Vega', 'f', '1959-04-21', 'TR'),
(34, 'Ethel', 'Witt', 'f', '1961-01-21', 'GB'),
(35, 'Anoushka', 'O\'Neill', 'f', '1961-10-25', 'IE'),
(36, 'Aaron', 'Cope', 'm', '1962-05-19', 'DK'),
(37, 'Reanna', 'Duggan', 'f', '1965-04-24', 'GB'),
(38, 'Juno', 'Bannister', 'm', '1968-08-08', 'IT'),
(39, 'Ivy-Rose', 'Sweet', 'f', '1971-04-25', 'US'),
(40, 'Kianna', 'Kirk', 'f', '1971-07-20', 'ZA'),
(41, 'Denis', 'Meadows', 'm', '1971-09-14', 'ZA'),
(42, 'Cassandra', 'Barr', 'f', '1972-11-05', 'US'),
(43, 'Abbie', 'Roberson', 'f', '1975-02-12', 'AU'),
(44, 'May', 'Gilmour', 'f', '1978-04-04', 'AU'),
(45, 'Fayne', 'Kaye', 'f', '1981-11-25', 'GB'),
(46, 'Connagh', 'Beil', 'm', '1986-06-07', 'DE'),
(47, 'Luqman', 'Hebert', 'm', '1987-05-30', 'DE'),
(48, 'Sofie', 'Calderon', 'f', '1989-02-24', 'DE'),
(49, 'Hayleigh', 'Cole', 'f', '1992-02-15', 'US'),
(50, 'Bonita', 'Ratliff', 'f', '1994-01-27', 'IT'),
(51, 'Otto', 'Shepard', 'm', '1953-06-27', 'DE'),
(52, 'Robyn', 'Cornish', 'm', '1955-06-09', 'DE'),
(53, 'Eshal', 'Hernandez', 'f', '1956-09-04', 'ES'),
(54, 'Zhane', 'Buchanan', 'f', '1958-01-17', 'IT'),
(55, 'Merryn', 'Beach', 'f', '1958-06-06', 'MA'),
(56, 'Mike', 'Mcgill', 'm', '1959-07-25', 'US'),
(57, 'Ishmael', 'Carrillo', 'm', '1960-09-09', 'MA'),
(58, 'Koa', 'Willis', 'm', '1968-02-27', 'MA'),
(59, 'Atticus', 'Melendez', 'm', '1968-05-18', 'ES'),
(60, 'Khalil', 'Nava', 'm', '1969-12-17', 'TR'),
(61, 'Tim', 'Roche', 'm', '1973-09-07', 'DE'),
(62, 'Jean-Luc', 'Major', 'm', '1973-10-26', 'FR'),
(63, 'Brooklyn', 'Flower', 'm', '1973-12-22', 'US'),
(64, 'Luis', 'Chavez', 'm', '1977-06-09', 'ES'),
(65, 'Ammara', 'Rogers', 'f', '1978-03-11', 'IT'),
(66, 'Erika', 'Walsh', 'f', '1978-08-26', 'DE'),
(67, 'Mimi', 'Jackson', 'f', '1978-11-05', 'DE'),
(68, 'Boris', 'Arellano', 'm', '1980-07-08', 'GB'),
(69, 'Yaseen', 'McCormack', 'f', '1980-10-02', 'GB'),
(70, 'Maksymilian', 'Sevenik', 'm', '1981-08-31', 'TR'),
(71, 'Iman', 'Lim', 'm', '1983-05-16', 'TR'),
(72, 'Azaan', 'Fuentes', 'm', '1983-12-23', 'MA'),
(73, 'Ahmet', 'Keith', 'm', '1985-08-25', 'MA'),
(74, 'Wallace', 'Chapman', 'm', '1987-05-12', 'US'),
(75, 'Maria', 'Shah', 'f', '1987-07-30', 'US'),
(76, 'Jannat', 'Allman', 'f', '1950-08-29', 'GB'),
(77, 'Gail', 'Mitchell', 'f', '1951-08-25', 'GB'),
(78, 'Shayaan', 'Humphreys', 'f', '1955-06-12', 'IE'),
(79, 'Rheanna', 'Miller', 'f', '1955-11-06', 'US'),
(80, 'Mylie', 'Maguie', 'f', '1959-03-18', 'US');

-- --------------------------------------------------------

--
-- Table structure for table `tblports`
--

CREATE TABLE `tblports` (
  `LOCODE` varchar(8) NOT NULL,
  `name` varchar(64) NOT NULL,
  `location` varchar(64) NOT NULL,
  `country` varchar(4) NOT NULL,
  `waters` varchar(64) DEFAULT NULL,
  `degree_lat` double NOT NULL,
  `arcminute_lat` double NOT NULL,
  `arcsecond_lat` double NOT NULL,
  `cardinal_point_lat` char(1) NOT NULL,
  `degree_long` double NOT NULL,
  `arcminute_long` double NOT NULL,
  `arcsecond_long` double NOT NULL,
  `cardinal_point_long` char(1) NOT NULL,
  `operator` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblports`
--

INSERT INTO `tblports` (`LOCODE`, `name`, `location`, `country`, `waters`, `degree_lat`, `arcminute_lat`, `arcsecond_lat`, `cardinal_point_lat`, `degree_long`, `arcminute_long`, `arcsecond_long`, `cardinal_point_long`, `operator`) VALUES
('AUSYD', 'Port Jackson', 'Sydney', 'AU', 'Botany Bay', 33, 51, 30, 'S', 151, 14, 0, 'E', 'Sydney Ports Corporation'),
('DEHAM', 'Port of Hamburg', 'Hamburg', 'DE', 'Elbe', 53, 32, 23.63, 'N', 9, 58, 57.86, 'E', 'Hamburg Port Authority'),
('DEPAP', 'Port of Papenburg', 'Papenburg', 'DE', 'Ems', 53, 5, 55, 'N', 7, 21, 59, 'E', 'Seaports of Niedersachsen GmbH'),
('DERSK', 'Rostock Port', 'Rostock', 'DE', 'Unterwarnow', 54, 8, 52.44, 'N', 12, 6, 55.1, 'E', 'Rostock Port GmbH'),
('DKCPH', 'Port of Copenhagen', 'Copenhagen', 'DK', 'Öresund', 55, 41, 46, 'N', 12, 36, 48, 'E', 'Copenhagen Malmö Port AB'),
('ESLPA', 'Port of Las Palmas', 'Las Palmas de Gran Canaria', 'ES', '', 28, 8, 14, 'N', 15, 25, 6, 'W', 'Autoridad Portuaria de Las Palmas'),
('GBLIV', 'Port of Liverpool', 'Liverpool', 'GB', 'Irish Sea', 53, 26, 11, 'N', 3, 0, 41, 'W', 'Mersey Docks and Harbour Comp.'),
('GBLON', 'Port of London', 'London', 'GB', 'Themse', 51, 30, 10, 'N', 0, 4, 3, 'E', 'Port of London Authority'),
('GBSOU', 'Port of Southampton', 'Southampton', 'GB', 'Solent', 50, 54, 9, 'N', 1, 25, 44, 'W', 'Associated British Ports'),
('JPOSA', 'Port of Osaka', 'Osaka', 'JP', 'Osaka Bay', 34, 38, 9, 'N', 135, 26, 3, 'E', 'Osaka Port Corporation'),
('JPYOK', 'Port of Yokohama', 'Yokohama', 'JP', 'Tokyo Bay', 35, 26, 11, 'N', 139, 40, 4, 'E', 'Port and Harbour Bureau City of Yokohama'),
('MAAGA', 'Port of Agadir', 'Agadir', 'MA', 'Nothern Atlantic', 30, 25, 26, 'N', 9, 38, 9, 'W', 'Societe D\'Exploitation des Ports (SODEP)'),
('NLAMS', 'Port of Amsterdam', 'Amsterdam', 'NL', 'North Sea Canal', 52, 24, 47, 'N', 4, 49, 42, 'E', 'Amsterdam Port Authority'),
('NLRTM', 'Port of Rotterdam', 'Rotterdam', 'NL', 'Rhine–Meuse–Scheldt delta', 51, 53, 34.87, 'N', 4, 18, 33.38, 'E', 'Port of Rotterdam Authority'),
('NZWLG', 'Wellington Harbour', 'Wellington', 'NZ', 'Cook Strait', 41, 17, 0, 'S', 174, 50, 0, 'E', 'CentrePort Wellington'),
('SGSIN', 'Port of Singapore', 'Singapur', 'SG', 'Strait of Malacca', 1, 17, 34, 'N', 103, 43, 31, 'E', 'Maritime and Port Authority'),
('TRIZM', 'Port of Izmir', 'Izmir', 'TR', 'Gulf of ?zmir', 38, 26, 32, 'N', 27, 9, 9, 'E', 'TCCD'),
('USBAL', 'Helen Delich Bentley Port of Baltimore', 'Baltimore', 'US', 'Chesapeake Bay', 39, 16, 30, 'N', 76, 35, 4.2, 'W', 'Maryland Port Administration'),
('USBOS', 'Port of Boston', 'Boston', 'US', 'Massachusetts Bay', 42, 22, 23.56, 'N', 71, 3, 19.99, 'W', 'Massport'),
('USMIA', 'Dante B. Fascell Port of Miami', 'Miami', 'US', 'Biscayne Bay', 25, 46, 27, 'N', 80, 10, 16, 'W', 'Miami Dade County'),
('USNYC', 'New York Harbor', 'New York City', 'US', 'Hudson River', 40, 41, 18, 'N', 74, 1, 43, 'W', 'Port Authority of New York and New Jersey'),
('ZACPT', 'Port of Cape Town', 'Cape Town', 'ZA', 'Table Bay', 33, 54, 33, 'S', 18, 26, 11, 'E', 'Transnet National Ports Authority');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblcabins`
--
ALTER TABLE `tblcabins`
  ADD PRIMARY KEY (`cabinNo`),
  ADD KEY `type` (`type`);

--
-- Indexes for table `tblcabintypes`
--
ALTER TABLE `tblcabintypes`
  ADD PRIMARY KEY (`type`);

--
-- Indexes for table `tblcountries`
--
ALTER TABLE `tblcountries`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `tblcrewpersons`
--
ALTER TABLE `tblcrewpersons`
  ADD PRIMARY KEY (`crewpersonNo`),
  ADD KEY `nationality` (`nationality`),
  ADD KEY `position` (`position`);

--
-- Indexes for table `tblcrewpositions`
--
ALTER TABLE `tblcrewpositions`
  ADD PRIMARY KEY (`position`);

--
-- Indexes for table `tblcruises`
--
ALTER TABLE `tblcruises`
  ADD PRIMARY KEY (`cruiseNo`,`passengerNo`) USING BTREE,
  ADD KEY `passengerNo` (`passengerNo`),
  ADD KEY `cabinNo` (`cabinNo`),
  ADD KEY `origin_port_LOCODE` (`origin_port_LOCODE`),
  ADD KEY `destination_port_LOCODE` (`destination_port_LOCODE`);

--
-- Indexes for table `tblpassengers`
--
ALTER TABLE `tblpassengers`
  ADD PRIMARY KEY (`passengerNo`),
  ADD KEY `nationality` (`nationality`);

--
-- Indexes for table `tblports`
--
ALTER TABLE `tblports`
  ADD PRIMARY KEY (`LOCODE`),
  ADD KEY `country` (`country`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblcabins`
--
ALTER TABLE `tblcabins`
  MODIFY `cabinNo` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=127;

--
-- AUTO_INCREMENT for table `tblcrewpersons`
--
ALTER TABLE `tblcrewpersons`
  MODIFY `crewpersonNo` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT for table `tblpassengers`
--
ALTER TABLE `tblpassengers`
  MODIFY `passengerNo` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblcabins`
--
ALTER TABLE `tblcabins`
  ADD CONSTRAINT `tblcabins_ibfk_1` FOREIGN KEY (`type`) REFERENCES `tblcabintypes` (`type`) ON UPDATE CASCADE;

--
-- Constraints for table `tblcrewpersons`
--
ALTER TABLE `tblcrewpersons`
  ADD CONSTRAINT `tblcrewpersons_ibfk_1` FOREIGN KEY (`nationality`) REFERENCES `tblcountries` (`code`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tblcrewpersons_ibfk_2` FOREIGN KEY (`position`) REFERENCES `tblcrewpositions` (`position`) ON UPDATE CASCADE;

--
-- Constraints for table `tblcruises`
--
ALTER TABLE `tblcruises`
  ADD CONSTRAINT `tblcruises_ibfk_1` FOREIGN KEY (`passengerNo`) REFERENCES `tblpassengers` (`passengerNo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tblcruises_ibfk_2` FOREIGN KEY (`cabinNo`) REFERENCES `tblcabins` (`cabinNo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tblcruises_ibfk_3` FOREIGN KEY (`origin_port_LOCODE`) REFERENCES `tblports` (`LOCODE`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tblcruises_ibfk_4` FOREIGN KEY (`destination_port_LOCODE`) REFERENCES `tblports` (`LOCODE`) ON UPDATE CASCADE;

--
-- Constraints for table `tblpassengers`
--
ALTER TABLE `tblpassengers`
  ADD CONSTRAINT `tblpassengers_ibfk_1` FOREIGN KEY (`nationality`) REFERENCES `tblcountries` (`code`) ON UPDATE CASCADE;

--
-- Constraints for table `tblports`
--
ALTER TABLE `tblports`
  ADD CONSTRAINT `tblports_ibfk_1` FOREIGN KEY (`country`) REFERENCES `tblcountries` (`code`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
