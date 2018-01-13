/*
 * This file is part of the SCUBA smart card framework.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * Copyright (C) 2009 - 2018  The SCUBA team.
 *
 * $Id$
 */

package net.sf.scuba.data;

import java.io.Serializable;

/**
 * Unicode CLDR country codes.
 * Based on <a href="http://unicode.org/Public/cldr/">http://unicode.org/Public/cldr/</a>, with
 * some additional attributes from ISO 3166 and Wikipedia.
 *
 * @author Martijn Oostdijk (martijn.oostdijk@gmail.com)
 *
 * @version $Revision$
 */
public class UnicodeCountry extends Country implements Serializable {

  private static final long serialVersionUID = 7220597933847617853L;

  public static final Country AD = new UnicodeCountry(0x020, "AD", "AND", "Andorra", "Andorran");
  public static final Country AE = new UnicodeCountry(0x784, "AE", "ARE", "United Arab Emirates", "Emirati, Emirian");
  public static final Country AF = new UnicodeCountry(0x004, "AF", "AFG", "Afghanistan", "Afghan");
  public static final Country AG = new UnicodeCountry(0x028, "AG", "ATG", "Antigua and Barbuda", "Antiguan, Barbudan");
  public static final Country AI = new UnicodeCountry(0x660, "AI", "AIA", "Anguilla", "Anguillan");
  public static final Country AL = new UnicodeCountry(0x008, "AL", "ALB", "Albania", "Albanian");
  public static final Country AM = new UnicodeCountry(0x051, "AM", "ARM", "Armenia", "Armenian");
  public static final Country AN = new UnicodeCountry(0x530, "AN", "ANT", "Netherlands Antilles", "Antillean");
  public static final Country AO = new UnicodeCountry(0x024, "AO", "AGO", "Angola", "Angolan");
  public static final Country AQ = new UnicodeCountry(0x010, "AQ", "ATA", "Antarctica", "Antarctic");
  public static final Country AR = new UnicodeCountry(0x032, "AR", "ARG", "Argentina", "Argentine, Argentinean, Argentinian");
  public static final Country AS = new UnicodeCountry(0x016, "AS", "ASM", "American Samoa", "American Samoan");
  public static final Country AT = new UnicodeCountry(0x040, "AT", "AUT", "Austria", "Austrian");
  public static final Country AU = new UnicodeCountry(0x036, "AU", "AUS", "Australia", "Australian");
  public static final Country AW = new UnicodeCountry(0x533, "AW", "ABW", "Aruba", "Aruban");
  public static final Country AX = new UnicodeCountry(0x248, "AX", "ALA", "Åland Islands", "");
  public static final Country AZ = new UnicodeCountry(0x031, "AZ", "AZE", "Azerbaijan", "Azerbaijani, Azeri");
  public static final Country BA = new UnicodeCountry(0x070, "BA", "BIH", "Bosnia and Herzegovina", "Bosnian, Bosniak, Herzegovinian");
  public static final Country BB = new UnicodeCountry(0x052, "BB", "BRB", "Barbados", "Barbadian");
  public static final Country BD = new UnicodeCountry(0x050, "BD", "BGD", "Bangladesh", "Bangladeshi");
  public static final Country BE = new UnicodeCountry(0x056, "BE", "BEL", "Belgium", "Belgian");
  public static final Country BF = new UnicodeCountry(0x854, "BF", "BFA", "Burkina Faso", "Burkinabe");
  public static final Country BG = new UnicodeCountry(0x100, "BG", "BGR", "Bulgaria", "Bulgarian");
  public static final Country BH = new UnicodeCountry(0x048, "BH", "BHR", "Bahrain", "Bahraini");
  public static final Country BI = new UnicodeCountry(0x108, "BI", "BDI", "Burundi", "Burundian");
  public static final Country BJ = new UnicodeCountry(0x204, "BJ", "BEN", "Benin", "Beninese");
  public static final Country BL = new UnicodeCountry(0x652, "BL", "BLM", "Saint Barthélemy", "");
  public static final Country BM = new UnicodeCountry(0x060, "BM", "BMU", "Bermuda", "Bermudian, Bermudan");
  public static final Country BN = new UnicodeCountry(0x096, "BN", "BRN", "Brunei", "Bruneian");
  public static final Country BO = new UnicodeCountry(0x068, "BO", "BOL", "Bolivia", "Bolivian");
  public static final Country BR = new UnicodeCountry(0x076, "BR", "BRA", "Brazil", "Brazilian");
  public static final Country BS = new UnicodeCountry(0x044, "BS", "BHS", "Bahamas", "Bahamian");
  public static final Country BT = new UnicodeCountry(0x064, "BT", "BTN", "Bhutan", "Bhutanese");
  public static final Country BV = new UnicodeCountry(0x074, "BV", "BVT", "Bouvet Island", "");
  public static final Country BW = new UnicodeCountry(0x072, "BW", "BWA", "Botswana", "Botswanan");
  public static final Country BY = new UnicodeCountry(0x112, "BY", "BLR", "Belarus", "Belarusian");
  public static final Country BZ = new UnicodeCountry(0x084, "BZ", "BLZ", "Belize", "Belizean");
  public static final Country CA = new UnicodeCountry(0x124, "CA", "CAN", "Canada", "Canadian");
  public static final Country CC = new UnicodeCountry(0x166, "CC", "CCK", "Cocos [Keeling] Islands", "");
  public static final Country CD = new UnicodeCountry(0x180, "CD", "COD", "Congo - Kinshasa", "Congolese");
  public static final Country CF = new UnicodeCountry(0x140, "CF", "CAF", "Central African Republic", "Central African");
  public static final Country CG = new UnicodeCountry(0x178, "CG", "COG", "Congo - Brazzaville", "Congolese");
  public static final Country CH = new UnicodeCountry(0x756, "CH", "CHE", "Switzerland", "Swiss");
  public static final Country CI = new UnicodeCountry(0x384, "CI", "CIV", "Côte d’Ivoire", "Ivorian");
  public static final Country CK = new UnicodeCountry(0x184, "CK", "COK", "Cook Islands", "");
  public static final Country CL = new UnicodeCountry(0x152, "CL", "CHL", "Chile", "Chilean");
  public static final Country CM = new UnicodeCountry(0x120, "CM", "CMR", "Cameroon", "Cameroonian");
  public static final Country CN = new UnicodeCountry(0x156, "CN", "CHN", "China", "Chinese");
  public static final Country CO = new UnicodeCountry(0x170, "CO", "COL", "Colombia", "Colombian");
  public static final Country CR = new UnicodeCountry(0x188, "CR", "CRI", "Costa Rica", "Costa Rican");
  public static final Country CU = new UnicodeCountry(0x192, "CU", "CUB", "Cuba", "Cuban");
  public static final Country CV = new UnicodeCountry(0x132, "CV", "CPV", "Cape Verde", "Cape Verdean");
  public static final Country CX = new UnicodeCountry(0x162, "CX", "CXR", "Christmas Island", "");
  public static final Country CY = new UnicodeCountry(0x196, "CY", "CYP", "Cyprus", "Cypriot");
  public static final Country CZ = new UnicodeCountry(0x203, "CZ", "CZE", "Czech Republic", "Czech");
  public static final Country DE = new UnicodeCountry(0x276, "DE", "DEU", "Germany", "German");
  public static final Country DJ = new UnicodeCountry(0x262, "DJ", "DJI", "Djibouti", "Djiboutian");
  public static final Country DK = new UnicodeCountry(0x208, "DK", "DNK", "Denmark", "Danish");
  public static final Country DM = new UnicodeCountry(0x212, "DM", "DMA", "Dominica", "Dominican");
  public static final Country DO = new UnicodeCountry(0x214, "DO", "DOM", "Dominican Republic", "Dominican");
  public static final Country DZ = new UnicodeCountry(0x012, "DZ", "DZA", "Algeria", "Algerian");
  public static final Country EC = new UnicodeCountry(0x218, "EC", "ECU", "Ecuador", "Ecuadorian");
  public static final Country EE = new UnicodeCountry(0x233, "EE", "EST", "Estonia", "Estonian");
  public static final Country EG = new UnicodeCountry(0x818, "EG", "EGY", "Egypt", "Egyptian");
  public static final Country EH = new UnicodeCountry(0x732, "EH", "ESH", "Western Sahara", "Sahraw, Sahrawian, Sahraouian");
  public static final Country ER = new UnicodeCountry(0x232, "ER", "ERI", "Eritrea", "Eritrean");
  public static final Country ES = new UnicodeCountry(0x724, "ES", "ESP", "Spain", "Spanish");
  public static final Country ET = new UnicodeCountry(0x231, "ET", "ETH", "Ethiopia", "Ethiopian");
  public static final Country FI = new UnicodeCountry(0x246, "FI", "FIN", "Finland", "Finnish");
  public static final Country FJ = new UnicodeCountry(0x242, "FJ", "FJI", "Fiji", "Fijian");
  public static final Country FK = new UnicodeCountry(0x238, "FK", "FLK", "Falkland Islands", "");
  public static final Country FM = new UnicodeCountry(0x583, "FM", "FSM", "Micronesia", "Micronesian");
  public static final Country FO = new UnicodeCountry(0x234, "FO", "FRO", "Faroe Islands", "Faroese");
  public static final Country FR = new UnicodeCountry(0x250, "FR", "FRA", "France", "French");
  public static final Country GA = new UnicodeCountry(0x266, "GA", "GAB", "Gabon", "Gabonese");
  public static final Country GB = new UnicodeCountry(0x826, "GB", "GBR", "United Kingdom", "British");
  public static final Country GD = new UnicodeCountry(0x308, "GD", "GRD", "Grenada", "Grenadian");
  public static final Country GE = new UnicodeCountry(0x268, "GE", "GEO", "Georgia", "Georgian");
  public static final Country GF = new UnicodeCountry(0x254, "GF", "GUF", "French Guiana", "French Guianese");
  public static final Country GG = new UnicodeCountry(0x831, "GG", "GGY", "Guernsey", "");
  public static final Country GH = new UnicodeCountry(0x288, "GH", "GHA", "Ghana", "Ghanaian");
  public static final Country GI = new UnicodeCountry(0x292, "GI", "GIB", "Gibraltar", "");
  public static final Country GL = new UnicodeCountry(0x304, "GL", "GRL", "Greenland", "Greenlandic");
  public static final Country GM = new UnicodeCountry(0x270, "GM", "GMB", "Gambia", "Gambian");
  public static final Country GN = new UnicodeCountry(0x324, "GN", "GIN", "Guinea", "Guinean");
  public static final Country GP = new UnicodeCountry(0x312, "GP", "GLP", "Guadeloupe", "");
  public static final Country GQ = new UnicodeCountry(0x226, "GQ", "GNQ", "Equatorial Guinea", "Equatorial Guinean, Equatoguinean");
  public static final Country GR = new UnicodeCountry(0x300, "GR", "GRC", "Greece", "Greek, Hellenic");
  public static final Country GS = new UnicodeCountry(0x239, "GS", "SGS", "South Georgia and the South Sandwich Islands", "");
  public static final Country GT = new UnicodeCountry(0x320, "GT", "GTM", "Guatemala", "Guatemalan");
  public static final Country GU = new UnicodeCountry(0x316, "GU", "GUM", "Guam", "Guamanian");
  public static final Country GW = new UnicodeCountry(0x624, "GW", "GNB", "Guinea-Bissau", "Guinean");
  public static final Country GY = new UnicodeCountry(0x328, "GY", "GUY", "Guyana", "Guyanese");
  public static final Country HK = new UnicodeCountry(0x344, "HK", "HKG", "Hong Kong SAR China", "Hong Kong, Hongkongese");
  public static final Country HM = new UnicodeCountry(0x334, "HM", "HMD", "Heard Island and McDonald Islands", "");
  public static final Country HN = new UnicodeCountry(0x340, "HN", "HND", "Honduras", "Honduran");
  public static final Country HR = new UnicodeCountry(0x191, "HR", "HRV", "Croatia", "Croatian");
  public static final Country HT = new UnicodeCountry(0x332, "HT", "HTI", "Haiti", "Haitian");
  public static final Country HU = new UnicodeCountry(0x348, "HU", "HUN", "Hungary", "Hungarian");
  public static final Country ID = new UnicodeCountry(0x360, "ID", "IDN", "Indonesia", "Indonesian");
  public static final Country IE = new UnicodeCountry(0x372, "IE", "IRL", "Ireland", "Irish");
  public static final Country IL = new UnicodeCountry(0x376, "IL", "ISR", "Israel", "Israeli");
  public static final Country IM = new UnicodeCountry(0x833, "IM", "IMN", "Isle of Man", "Manx");
  public static final Country IN = new UnicodeCountry(0x356, "IN", "IND", "India", "Indian");
  public static final Country IO = new UnicodeCountry(0x086, "IO", "IOT", "British Indian Ocean Territory", "");
  public static final Country IQ = new UnicodeCountry(0x368, "IQ", "IRQ", "Iraq", "Iraqi");
  public static final Country IR = new UnicodeCountry(0x364, "IR", "IRN", "Iran", "Iranian, Persian");
  public static final Country IS = new UnicodeCountry(0x352, "IS", "ISL", "Iceland", "Icelandic");
  public static final Country IT = new UnicodeCountry(0x380, "IT", "ITA", "Italy", "Italian");
  public static final Country JE = new UnicodeCountry(0x832, "JE", "JEY", "Jersey", "");
  public static final Country JM = new UnicodeCountry(0x388, "JM", "JAM", "Jamaica", "Jamaican");
  public static final Country JO = new UnicodeCountry(0x400, "JO", "JOR", "Jordan", "Jordanian");
  public static final Country JP = new UnicodeCountry(0x392, "JP", "JPN", "Japan", "Japanese");
  public static final Country KE = new UnicodeCountry(0x404, "KE", "KEN", "Kenya", "Kenyan");
  public static final Country KG = new UnicodeCountry(0x417, "KG", "KGZ", "Kyrgyzstan", "Kyrgyz");
  public static final Country KH = new UnicodeCountry(0x116, "KH", "KHM", "Cambodia", "Cambodian");
  public static final Country KI = new UnicodeCountry(0x296, "KI", "KIR", "Kiribati", "I-Kiribati");
  public static final Country KM = new UnicodeCountry(0x174, "KM", "COM", "Comoros", "Comorian");
  public static final Country KN = new UnicodeCountry(0x659, "KN", "KNA", "Saint Kitts and Nevis", "");
  public static final Country KP = new UnicodeCountry(0x408, "KP", "PRK", "North Korea", "North Korean");
  public static final Country KR = new UnicodeCountry(0x410, "KR", "KOR", "South Korea", "South Korean");
  public static final Country KW = new UnicodeCountry(0x414, "KW", "KWT", "Kuwait", "Kuwaiti");
  public static final Country KY = new UnicodeCountry(0x136, "KY", "CYM", "Cayman Islands", "Caymanian");
  public static final Country KZ = new UnicodeCountry(0x398, "KZ", "KAZ", "Kazakhstan", "Kazakh");
  public static final Country LA = new UnicodeCountry(0x418, "LA", "Lao", "Laos", "Lao");
  public static final Country LB = new UnicodeCountry(0x422, "LB", "LBN", "Lebanon", "Lebanese");
  public static final Country LC = new UnicodeCountry(0x662, "LC", "LCA", "Saint Lucia", "Saint Lucian");
  public static final Country LI = new UnicodeCountry(0x438, "LI", "LIE", "Liechtenstein", "");
  public static final Country LK = new UnicodeCountry(0x144, "LK", "LKA", "Sri Lanka", "Sri Lankan");
  public static final Country LR = new UnicodeCountry(0x430, "LR", "LBR", "Liberia", "Liberian");
  public static final Country LS = new UnicodeCountry(0x426, "LS", "LSO", "Lesotho", "Basotho");
  public static final Country LT = new UnicodeCountry(0x440, "LT", "LTU", "Lithuania", "Lithuanian");
  public static final Country LU = new UnicodeCountry(0x442, "LU", "LUX", "Luxembourg", "Luxembourg, Luxembourgish");
  public static final Country LV = new UnicodeCountry(0x428, "LV", "LVA", "Latvia", "Latvian");
  public static final Country LY = new UnicodeCountry(0x434, "LY", "LBY", "Libya", "Libyan");
  public static final Country MA = new UnicodeCountry(0x504, "MA", "MAR", "Morocco", "Moroccan");
  public static final Country MC = new UnicodeCountry(0x492, "MC", "MCO", "Monaco", "Monegasque, Monacan");
  public static final Country MD = new UnicodeCountry(0x498, "MD", "MDA", "Moldova", "Moldovan");
  public static final Country ME = new UnicodeCountry(0x499, "ME", "MNE", "Montenegro", "Montenegrin");
  public static final Country MF = new UnicodeCountry(0x663, "MF", "MAF", "Saint Martin", "");
  public static final Country MG = new UnicodeCountry(0x450, "MG", "MDG", "Madagascar", "Malagasy");
  public static final Country MH = new UnicodeCountry(0x584, "MH", "MHL", "Marshall Islands", "Marshallese");
  public static final Country MK = new UnicodeCountry(0x807, "MK", "MKD", "Macedonia", "Macedonian");
  public static final Country ML = new UnicodeCountry(0x466, "ML", "MLI", "Mali", "Malian");
  public static final Country MM = new UnicodeCountry(0x104, "MM", "MMR", "Myanmar [Burma]", "Burmese");
  public static final Country MN = new UnicodeCountry(0x496, "MN", "MNG", "Mongolia", "Mongolian");
  public static final Country MO = new UnicodeCountry(0x446, "MO", "MAC", "Macau SAR China", "Macanese, Chinese");
  public static final Country MP = new UnicodeCountry(0x580, "MP", "MNP", "Northern Mariana Islands", "Northern Marianan");
  public static final Country MQ = new UnicodeCountry(0x474, "MQ", "MTQ", "Martinique", "Martiniquais, Martinican");
  public static final Country MR = new UnicodeCountry(0x478, "MR", "MRT", "Mauritania", "Mauritanian");
  public static final Country MS = new UnicodeCountry(0x500, "MS", "MSR", "Montserrat", "Montserratian");
  public static final Country MT = new UnicodeCountry(0x470, "MT", "MLT", "Malta", "Maltese");
  public static final Country MU = new UnicodeCountry(0x480, "MU", "MUS", "Mauritius", "Mauritian");
  public static final Country MV = new UnicodeCountry(0x462, "MV", "MDV", "Maldives", "Maldivian");
  public static final Country MW = new UnicodeCountry(0x454, "MW", "MWI", "Malawi", "Malawian");
  public static final Country MX = new UnicodeCountry(0x484, "MX", "MEX", "Mexico", "Mexican");
  public static final Country MY = new UnicodeCountry(0x458, "MY", "MYS", "Malaysia", "Malaysian");
  public static final Country MZ = new UnicodeCountry(0x508, "MZ", "MOZ", "Mozambique", "Mozambican");
  public static final Country NA = new UnicodeCountry(0x516, "NA", "NAM", "Namibia", "Namibian");
  public static final Country NC = new UnicodeCountry(0x540, "NC", "NCL", "New Caledonia", "New Caledonian");
  public static final Country NE = new UnicodeCountry(0x562, "NE", "NER", "Niger", "Nigerien");
  public static final Country NF = new UnicodeCountry(0x574, "NF", "NFK", "Norfolk Island", "");
  public static final Country NG = new UnicodeCountry(0x566, "NG", "NGA", "Nigeria", "Nigerian");
  public static final Country NI = new UnicodeCountry(0x558, "NI", "NIC", "Nicaragua", "Nicaraguan");
  public static final Country NL = new UnicodeCountry(0x528, "NL", "NLD", "Netherlands", "Dutch");
  public static final Country NO = new UnicodeCountry(0x578, "NO", "NOR", "Norway", "Norwegian");
  public static final Country NP = new UnicodeCountry(0x524, "NP", "NPL", "Nepal", "Nepali");
  public static final Country NR = new UnicodeCountry(0x520, "NR", "NRU", "Nauru", "Nauruan");
  public static final Country NU = new UnicodeCountry(0x570, "NU", "NIU", "Niue", "Niuean");
  public static final Country NZ = new UnicodeCountry(0x554, "NZ", "NZL", "New Zealand", "");
  public static final Country OM = new UnicodeCountry(0x512, "OM", "OMN", "Oman", "Omani");
  public static final Country PA = new UnicodeCountry(0x591, "PA", "PAN", "Panama", "Panamanian");
  public static final Country PE = new UnicodeCountry(0x604, "PE", "PER", "Peru", "Peruvian");
  public static final Country PF = new UnicodeCountry(0x258, "PF", "PYF", "French Polynesia", "French Polynesian");
  public static final Country PG = new UnicodeCountry(0x598, "PG", "PNG", "Papua New Guinea", "Papua New Guinean, Papuan");
  public static final Country PH = new UnicodeCountry(0x608, "PH", "PHL", "Philippines", "Philippine, Filipino");
  public static final Country PK = new UnicodeCountry(0x586, "PK", "PAK", "Pakistan", "Pakistani");
  public static final Country PL = new UnicodeCountry(0x616, "PL", "POL", "Poland", "Polish");
  public static final Country PM = new UnicodeCountry(0x666, "PM", "SPM", "Saint Pierre and Miquelon", "Saint Pierrais, Miquelonnais");
  public static final Country PN = new UnicodeCountry(0x612, "PN", "PCN", "Pitcairn Islands", "");
  public static final Country PR = new UnicodeCountry(0x630, "PR", "PRI", "Puerto Rico", "Puerto Rican");
  public static final Country PS = new UnicodeCountry(0x275, "PS", "PSE", "Palestinian Territories", "Palestinian");
  public static final Country PT = new UnicodeCountry(0x620, "PT", "PRT", "Portugal", "Portuguese");
  public static final Country PW = new UnicodeCountry(0x585, "PW", "PLW", "Palau", "Palauan");
  public static final Country PY = new UnicodeCountry(0x600, "PY", "PRY", "Paraguay", "Paraguayan");
  public static final Country QA = new UnicodeCountry(0x634, "QA", "QAT", "Qatar", "Qatari");
  public static final Country RE = new UnicodeCountry(0x638, "RE", "REU", "Réunion", "Reunionese, Reunionnais");
  public static final Country RO = new UnicodeCountry(0x642, "RO", "ROU", "Romania", "Romanian");
  public static final Country RS = new UnicodeCountry(0x688, "RS", "SRB", "Serbia", "Serbian");
  public static final Country RU = new UnicodeCountry(0x643, "RU", "RUS", "Russia", "Russian");
  public static final Country RW = new UnicodeCountry(0x646, "RW", "RWA", "Rwanda", "Rwandan");
  public static final Country SA = new UnicodeCountry(0x682, "SA", "SAU", "Saudi Arabia", "Saudi, Saudi Arabian");
  public static final Country SB = new UnicodeCountry(0x090, "SB", "SLB", "Solomon Islands", "Solomon Island");
  public static final Country SC = new UnicodeCountry(0x690, "SC", "SYC", "Seychelles", "Seychellois");
  public static final Country SD = new UnicodeCountry(0x736, "SD", "SDN", "Sudan", "Sudanese");
  public static final Country SE = new UnicodeCountry(0x752, "SE", "SWE", "Sweden", "Swedish");
  public static final Country SG = new UnicodeCountry(0x702, "SG", "SGP", "Singapore", "");
  public static final Country SH = new UnicodeCountry(0x654, "SH", "SHN", "Saint Helena", "Saint Helenian");
  public static final Country SI = new UnicodeCountry(0x705, "SI", "SVN", "Slovenia", "Slovenian, Slovene");
  public static final Country SJ = new UnicodeCountry(0x744, "SJ", "SJM", "Svalbard and Jan Mayen", "");
  public static final Country SK = new UnicodeCountry(0x703, "SK", "SVK", "Slovakia", "Slovak");
  public static final Country SL = new UnicodeCountry(0x694, "SL", "SLE", "Sierra Leone", "Sierra Leonean");
  public static final Country SM = new UnicodeCountry(0x674, "SM", "SMR", "San Marino", "Sammarinese");
  public static final Country SN = new UnicodeCountry(0x686, "SN", "SEN", "Senegal", "Senegalese");
  public static final Country SO = new UnicodeCountry(0x706, "SO", "SOM", "Somalia", "Somali, Somalian");
  public static final Country SR = new UnicodeCountry(0x740, "SR", "SUR", "Suriname", "Surinamese");
  public static final Country ST = new UnicodeCountry(0x678, "ST", "STP", "São Tomé & Príncipe", "Sao Tomean");
  public static final Country SV = new UnicodeCountry(0x222, "SV", "SLV", "El Salvador", "Salvadoran");
  public static final Country SY = new UnicodeCountry(0x760, "SY", "SYR", "Syria", "Syrian");
  public static final Country SZ = new UnicodeCountry(0x748, "SZ", "SWZ", "Swaziland", "Swazi");
  public static final Country TC = new UnicodeCountry(0x796, "TC", "TCA", "Turks and Caicos Islands", "");
  public static final Country TD = new UnicodeCountry(0x148, "TD", "TCD", "Chad", "Chadian");
  public static final Country TF = new UnicodeCountry(0x260, "TF", "ATF", "French Southern Territories", "");
  public static final Country TG = new UnicodeCountry(0x768, "TG", "TGO", "Togo", "Togolese");
  public static final Country TH = new UnicodeCountry(0x764, "TH", "THA", "Thailand", "Thai");
  public static final Country TJ = new UnicodeCountry(0x762, "TJ", "TJK", "Tajikistan", "Tajikistani");
  public static final Country TK = new UnicodeCountry(0x772, "TK", "TKL", "Tokelau", "");
  public static final Country TL = new UnicodeCountry(0x626, "TL", "TLS", "Timor-Leste", "Timorese");
  public static final Country TM = new UnicodeCountry(0x795, "TM", "TKM", "Turkmenistan", "Turkmen");
  public static final Country TN = new UnicodeCountry(0x788, "TN", "TUN", "Tunisia", "Tunisian");
  public static final Country TO = new UnicodeCountry(0x776, "TO", "TON", "Tonga", "Tongan");
  public static final Country TR = new UnicodeCountry(0x792, "TR", "TUR", "Turkey", "Turkish");
  public static final Country TT = new UnicodeCountry(0x780, "TT", "TTO", "Trinidad and Tobago", "Trinidadian, Tobagonian");
  public static final Country TV = new UnicodeCountry(0x798, "TV", "TUV", "Tuvalu", "Tuvaluan");
  public static final Country TW = new UnicodeCountry(0x158, "TW", "TWN", "Taiwan", "Taiwanese");
  public static final Country TZ = new UnicodeCountry(0x834, "TZ", "TZA", "Tanzania", "Tanzanian");
  public static final Country UA = new UnicodeCountry(0x804, "UA", "UKR", "Ukraine", "Ukrainian");
  public static final Country UG = new UnicodeCountry(0x800, "UG", "UGA", "Uganda", "Ugandan");
  public static final Country UM = new UnicodeCountry(0x581, "UM", "UMI", "U.S. Outlying Islands", "");
  public static final Country US = new UnicodeCountry(0x840, "US", "USA", "United States", "American");
  public static final Country UY = new UnicodeCountry(0x858, "UY", "URY", "Uruguay", "Uruguayan");
  public static final Country UZ = new UnicodeCountry(0x860, "UZ", "UZB", "Uzbekistan", "Uzbekistani, Uzbek");
  public static final Country VA = new UnicodeCountry(0x336, "VA", "VAT", "Vatican City", "");
  public static final Country VC = new UnicodeCountry(0x670, "VC", "VCT", "Saint Vincent and the Grenadines", "Saint Vincentian");
  public static final Country VE = new UnicodeCountry(0x862, "VE", "VEN", "Venezuela", "Venezuelan");
  public static final Country VG = new UnicodeCountry(0x092, "VG", "VGB", "British Virgin Islands", "Virgin Island");
  public static final Country VI = new UnicodeCountry(0x850, "VI", "VIR", "U.S. Virgin Islands", "Virgin Island");
  public static final Country VN = new UnicodeCountry(0x704, "VN", "VNM", "Vietnam", "Vietnamese");
  public static final Country VU = new UnicodeCountry(0x548, "VU", "VUT", "Vanuatu", "Ni-Vanuatu, Vanuatuan");
  public static final Country WF = new UnicodeCountry(0x876, "WF", "WLF", "Wallis and Futuna", "Wallisian, Futunan");
  public static final Country WS = new UnicodeCountry(0x882, "WS", "WSM", "Samoa", "Samoan");
  public static final Country YE = new UnicodeCountry(0x887, "YE", "YEM", "Yemen", "Yemeni");
  public static final Country YT = new UnicodeCountry(0x175, "YT", "MYT", "Mayotte", "Mahoran");
  public static final Country ZA = new UnicodeCountry(0x710, "ZA", "ZAF", "South Africa", "South African");
  public static final Country ZM = new UnicodeCountry(0x894, "ZM", "ZMB", "Zambia", "Zambian");
  public static final Country ZW = new UnicodeCountry(0x716, "ZW", "ZWE", "Zimbabwe", "Zimbabwean");

  private static final Country[] VALUES =
    {
        /* AC, */ AD, AE, AF, AG, AI, AL, AM, AN, AO, AQ, AR, AS, AT, AU, AW,
        AX, AZ, BA, BB, BD, BE, BF, BG, BH, BI, BJ, BL, BM, BN, BO, /* BQ, */
        BR, BS, BT, BV, BW, BY, BZ, CA, CC, CD, CF, CG, CH, CI, CK, CL,
        CM, CN, CO, /* CP, */ CR, CU, CV, /* CW, */ CX, CY, CZ, DE, /* DG, */ DJ, DK, DM,
        DO, DZ, /* EA, */ EC, EE, EG, EH, ER, ES, ET, /* EU, */ FI, FJ, FK, FM, FO,
        FR, GA, GB, GD, GE, GF, GG, GH, GI, GL, GM, GN, GP, GQ, GR, GS,
        GT, GU, GW, GY, HK, HM, HN, HR, HT, HU, /* IC, */ ID, IE, IL, IM, IN,
        IO, IQ, IR, IS, IT, JE, JM, JO, JP, KE, KG, KH, KI, KM, KN, KP,
        KR, KW, KY, KZ, LA, LB, LC, LI, LK, LR, LS, LT, LU, LV, LY, MA,
        MC, MD, ME, MF, MG, MH, MK, ML, MM, MN, MO, MP, MQ, MR, MS, MT,
        MU, MV, MW, MX, MY, MZ, NA, NC, NE, NF, NG, NI, NL, NO, NP, NR,
        NU, NZ, OM, PA, PE, PF, PG, PH, PK, PL, PM, PN, PR, PS, PT, PW,
        PY, QA, /* QO, */ RE, RO, RS, RU, RW, SA, SB, SC, SD, SE, SG, SH, SI,
        SJ, SK, SL, SM, SN, SO, SR, /* SS, */ ST, SV, /* SX, */ SY, SZ, /* TA, */ TC, TD,
        TF, TG, TH, TJ, TK, TL, TM, TN, TO, TR, TT, TV, TW, TZ, UA, UG,
        UM, US, UY, UZ, VA, VC, VE, VG, VI, VN, VU, WF, WS, /* XK, */ YE, YT,
        ZA, ZM, ZW
    };

  private int code;
  private String alpha2Code;
  private String alpha3Code;
  private String name;
  private String nationality;

  private UnicodeCountry(int code, String alpha2Code, String alpha3Code, String name, String nationality) {
    this.code = code;
    this.alpha2Code = alpha2Code;
    this.alpha3Code = alpha3Code;
    this.name = name;
    this.nationality = nationality;
  }

  public static Country[] values() {
    return VALUES;
  }

  @Override
  public int valueOf() {
    return code;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getNationality() {
    return nationality;
  }

  @Override
  public String toString() {
    return alpha2Code;
  }

  @Override
  public String toAlpha2Code() {
    return alpha2Code;
  }

  @Override
  public String toAlpha3Code() {
    return alpha3Code;
  }

  @Override
  public int hashCode() {
    return code;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!obj.getClass().equals(this.getClass())) {
      return false;
    }
    UnicodeCountry other = (UnicodeCountry)obj;
    return other.code == code;
  }

  public int compareTo(Country o) {
    return toAlpha2Code().compareTo(o.toAlpha2Code());
  }
}
