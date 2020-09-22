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
 * ISO 3166 country codes.
 * Table based on Wikipedia information.
 *
 * This used to be an enum.
 *
 * @author Martijn Oostdijk (martijn.oostdijk@gmail.com)
 *
 * @version $Revision$
 */
public class ISOCountry extends Country implements Serializable {

  private static final long serialVersionUID = 7220597933847617859L;

  /** ISO 3166 country. */
  public static final Country AD = new ISOCountry(0x020, "AD", "AND", "Andorra", "Andorran");

  /** ISO 3166 country. */
  public static final Country AE = new ISOCountry(0x784, "AE", "ARE", "United Arab Emirates", "Emirati, Emirian");

  /** ISO 3166 country. */
  public static final Country AF = new ISOCountry(0x004, "AF", "AFG", "Afghanistan", "Afghan");

  /** ISO 3166 country. */
  public static final Country AG = new ISOCountry(0x028, "AG", "ATG", "Antigua and Barbuda", "Antiguan, Barbudan");

  /** ISO 3166 country. */
  public static final Country AI = new ISOCountry(0x660, "AI", "AIA", "Anguilla", "Anguillan");

  /** ISO 3166 country. */
  public static final Country AL = new ISOCountry(0x008, "AL", "ALB", "Albania", "Albanian");

  /** ISO 3166 country. */
  public static final Country AM = new ISOCountry(0x051, "AM", "ARM", "Armenia", "Armenian");

  /** ISO 3166 country. */
  public static final Country AN = new ISOCountry(0x530, "AN", "ANT", "Netherlands Antilles", "Antillean");

  /** ISO 3166 country. */
  public static final Country AO = new ISOCountry(0x024, "AO", "AGO", "Angola", "Angolan");

  /** ISO 3166 country. */
  public static final Country AQ = new ISOCountry(0x010, "AQ", "ATA", "Antarctica", "Antarctic");

  /** ISO 3166 country. */
  public static final Country AR = new ISOCountry(0x032, "AR", "ARG", "Argentina", "Argentine, Argentinean, Argentinian");

  /** ISO 3166 country. */
  public static final Country AS = new ISOCountry(0x016, "AS", "ASM", "American Samoa", "American Samoan");

  /** ISO 3166 country. */
  public static final Country AT = new ISOCountry(0x040, "AT", "AUT", "Austria", "Austrian");

  /** ISO 3166 country. */
  public static final Country AU = new ISOCountry(0x036, "AU", "AUS", "Australia", "Australian");

  /** ISO 3166 country. */
  public static final Country AW = new ISOCountry(0x533, "AW", "ABW", "Aruba", "Aruban");

  /** ISO 3166 country. */
  public static final Country AX = new ISOCountry(0x248, "AX", "ALA", "Aland Islands");

  /** ISO 3166 country. */
  public static final Country AZ = new ISOCountry(0x031, "AZ", "AZE", "Azerbaijan", "Azerbaijani, Azeri");

  /** ISO 3166 country. */
  public static final Country BA = new ISOCountry(0x070, "BA", "BIH", "Bosnia and Herzegovina", "Bosnian, Bosniak, Herzegovinian");

  /** ISO 3166 country. */
  public static final Country BB = new ISOCountry(0x052, "BB", "BRB", "Barbados", "Barbadian");

  /** ISO 3166 country. */
  public static final Country BD = new ISOCountry(0x050, "BD", "BGD", "Bangladesh", "Bangladeshi");

  /** ISO 3166 country. */
  public static final Country BE = new ISOCountry(0x056, "BE", "BEL", "Belgium", "Belgian");

  /** ISO 3166 country. */
  public static final Country BF = new ISOCountry(0x854, "BF", "BFA", "Burkina Faso", "Burkinabe");

  /** ISO 3166 country. */
  public static final Country BG = new ISOCountry(0x100, "BG", "BGR", "Bulgaria", "Bulgarian");

  /** ISO 3166 country. */
  public static final Country BH = new ISOCountry(0x048, "BH", "BHR", "Bahrain", "Bahraini");

  /** ISO 3166 country. */
  public static final Country BI = new ISOCountry(0x108, "BI", "BDI", "Burundi", "Burundian");

  /** ISO 3166 country. */
  public static final Country BJ = new ISOCountry(0x204, "BJ", "BEN", "Benin", "Beninese");

  /** ISO 3166 country. */
  public static final Country BL = new ISOCountry(0x652, "BL", "BLM", "Saint Barthlemy");

  /** ISO 3166 country. */
  public static final Country BM = new ISOCountry(0x060, "BM", "BMU", "Bermuda", "Bermudian, Bermudan");

  /** ISO 3166 country. */
  public static final Country BN = new ISOCountry(0x096, "BN", "BRN", "Brunei Darussalam", "Bruneian");

  /** ISO 3166 country. */
  public static final Country BO = new ISOCountry(0x068, "BO", "BOL", "Bolivia", "Bolivian");

  /** ISO 3166 country. */
  public static final Country BR = new ISOCountry(0x076, "BR", "BRA", "Brazil", "Brazilian");

  /** ISO 3166 country. */
  public static final Country BS = new ISOCountry(0x044, "BS", "BHS", "Bahamas", "Bahamian");

  /** ISO 3166 country. */
  public static final Country BT = new ISOCountry(0x064, "BT", "BTN", "Bhutan", "Bhutanese");

  /** ISO 3166 country. */
  public static final Country BV = new ISOCountry(0x074, "BV", "BVT", "Bouvet Island");

  /** ISO 3166 country. */
  public static final Country BW = new ISOCountry(0x072, "BW", "BWA", "Botswana", "Botswanan");

  /** ISO 3166 country. */
  public static final Country BY = new ISOCountry(0x112, "BY", "BLR", "Belarus", "Belarusian");

  /** ISO 3166 country. */
  public static final Country BZ = new ISOCountry(0x084, "BZ", "BLZ", "Belize", "Belizean");

  /** ISO 3166 country. */
  public static final Country CA = new ISOCountry(0x124, "CA", "CAN", "Canada", "Canadian");

  /** ISO 3166 country. */
  public static final Country CC = new ISOCountry(0x166, "CC", "CCK", "Cocos (Keeling) Islands");

  /** ISO 3166 country. */
  public static final Country CD = new ISOCountry(0x180, "CD", "COD", "Congo the Democratic Republic of the", "Congolese");

  /** ISO 3166 country. */
  public static final Country CF = new ISOCountry(0x140, "CF", "CAF", "Central African Republic", "Central African");

  /** ISO 3166 country. */
  public static final Country CG = new ISOCountry(0x178, "CG", "COG", "Congo", "Congolese");

  /** ISO 3166 country. */
  public static final Country CH = new ISOCountry(0x756, "CH", "CHE", "Switzerland", "Swiss");

  /** ISO 3166 country. */
  public static final Country CI = new ISOCountry(0x384, "CI", "CIV", "Cote d'Ivoire", "Ivorian");

  /** ISO 3166 country. */
  public static final Country CK = new ISOCountry(0x184, "CK", "COK", "Cook Islands");

  /** ISO 3166 country. */
  public static final Country CL = new ISOCountry(0x152, "CL", "CHL", "Chile", "Chilean");

  /** ISO 3166 country. */
  public static final Country CM = new ISOCountry(0x120, "CM", "CMR", "Cameroon", "Cameroonian");

  /** ISO 3166 country. */
  public static final Country CN = new ISOCountry(0x156, "CN", "CHN", "China", "Chinese");

  /** ISO 3166 country. */
  public static final Country CO = new ISOCountry(0x170, "CO", "COL", "Colombia", "Colombian");

  /** ISO 3166 country. */
  public static final Country CR = new ISOCountry(0x188, "CR", "CRI", "Costa Rica", "Costa Rican");

  /** ISO 3166 country. */
  public static final Country CU = new ISOCountry(0x192, "CU", "CUB", "Cuba", "Cuban");

  /** ISO 3166 country. */
  public static final Country CV = new ISOCountry(0x132, "CV", "CPV", "Cape Verde", "Cape Verdean");

  /** ISO 3166 country. */
  public static final Country CX = new ISOCountry(0x162, "CX", "CXR", "Christmas Island");

  /** ISO 3166 country. */
  public static final Country CY = new ISOCountry(0x196, "CY", "CYP", "Cyprus", "Cypriot");

  /** ISO 3166 country. */
  public static final Country CZ = new ISOCountry(0x203, "CZ", "CZE", "Czech Republic", "Czech");

  /** ISO 3166 country. */
  public static final Country DE = new ISOCountry(0x276, "DE", "DEU", "Germany", "German");

  /** ISO 3166 country. */
  public static final Country DJ = new ISOCountry(0x262, "DJ", "DJI", "Djibouti", "Djiboutian");

  /** ISO 3166 country. */
  public static final Country DK = new ISOCountry(0x208, "DK", "DNK", "Denmark", "Danish");

  /** ISO 3166 country. */
  public static final Country DM = new ISOCountry(0x212, "DM", "DMA", "Dominica", "Dominican");

  /** ISO 3166 country. */
  public static final Country DO = new ISOCountry(0x214, "DO", "DOM", "Dominican Republic", "Dominican");

  /** ISO 3166 country. */
  public static final Country DZ = new ISOCountry(0x012, "DZ", "DZA", "Algeria", "Algerian");

  /** ISO 3166 country. */
  public static final Country EC = new ISOCountry(0x218, "EC", "ECU", "Ecuador", "Ecuadorian");

  /** ISO 3166 country. */
  public static final Country EE = new ISOCountry(0x233, "EE", "EST", "Estonia", "Estonian");

  /** ISO 3166 country. */
  public static final Country EG = new ISOCountry(0x818, "EG", "EGY", "Egypt", "Egyptian");

  /** ISO 3166 country. */
  public static final Country EH = new ISOCountry(0x732, "EH", "ESH", "Western Sahara", "Sahraw, Sahrawian, Sahraouian");

  /** ISO 3166 country. */
  public static final Country ER = new ISOCountry(0x232, "ER", "ERI", "Eritrea", "Eritrean");

  /** ISO 3166 country. */
  public static final Country ES = new ISOCountry(0x724, "ES", "ESP", "Spain", "Spanish");

  /** ISO 3166 country. */
  public static final Country ET = new ISOCountry(0x231, "ET", "ETH", "Ethiopia", "Ethiopian");

  /** ISO 3166 country. */
  public static final Country FI = new ISOCountry(0x246, "FI", "FIN", "Finland", "Finnish");

  /** ISO 3166 country. */
  public static final Country FJ = new ISOCountry(0x242, "FJ", "FJI", "Fiji", "Fijian");

  /** ISO 3166 country. */
  public static final Country FK = new ISOCountry(0x238, "FK", "FLK", "Falkland Islands (Malvinas)");

  /** ISO 3166 country. */
  public static final Country FM = new ISOCountry(0x583, "FM", "FSM", "Micronesia Federated States of", "Micronesian");

  /** ISO 3166 country. */
  public static final Country FO = new ISOCountry(0x234, "FO", "FRO", "Faroe Islands", "Faroese");

  /** ISO 3166 country. */
  public static final Country FR = new ISOCountry(0x250, "FR", "FRA", "France", "French");

  /** ISO 3166 country. */
  public static final Country GA = new ISOCountry(0x266, "GA", "GAB", "Gabon", "Gabonese");

  /** ISO 3166 country. */
  public static final Country GB = new ISOCountry(0x826, "GB", "GBR", "United Kingdom", "British");

  /** ISO 3166 country. */
  public static final Country GD = new ISOCountry(0x308, "GD", "GRD", "Grenada", "Grenadian");

  /** ISO 3166 country. */
  public static final Country GE = new ISOCountry(0x268, "GE", "GEO", "Georgia", "Georgian");

  /** ISO 3166 country. */
  public static final Country GF = new ISOCountry(0x254, "GF", "GUF", "French Guiana", "French Guianese");

  /** ISO 3166 country. */
  public static final Country GG = new ISOCountry(0x831, "GG", "GGY", "Guernsey");

  /** ISO 3166 country. */
  public static final Country GH = new ISOCountry(0x288, "GH", "GHA", "Ghana", "Ghanaian");

  /** ISO 3166 country. */
  public static final Country GI = new ISOCountry(0x292, "GI", "GIB", "Gibraltar");

  /** ISO 3166 country. */
  public static final Country GL = new ISOCountry(0x304, "GL", "GRL", "Greenland", "Greenlandic");

  /** ISO 3166 country. */
  public static final Country GM = new ISOCountry(0x270, "GM", "GMB", "Gambia", "Gambian");

  /** ISO 3166 country. */
  public static final Country GN = new ISOCountry(0x324, "GN", "GIN", "Guinea", "Guinean");

  /** ISO 3166 country. */
  public static final Country GP = new ISOCountry(0x312, "GP", "GLP", "Guadeloupe");

  /** ISO 3166 country. */
  public static final Country GQ = new ISOCountry(0x226, "GQ", "GNQ", "Equatorial Guinea", "Equatorial Guinean, Equatoguinean");

  /** ISO 3166 country. */
  public static final Country GR = new ISOCountry(0x300, "GR", "GRC", "Greece", "Greek, Hellenic");

  /** ISO 3166 country. */
  public static final Country GS = new ISOCountry(0x239, "GS", "SGS", "South Georgia and the South Sandwich Islands");

  /** ISO 3166 country. */
  public static final Country GT = new ISOCountry(0x320, "GT", "GTM", "Guatemala", "Guatemalan");

  /** ISO 3166 country. */
  public static final Country GU = new ISOCountry(0x316, "GU", "GUM", "Guam", "Guamanian");

  /** ISO 3166 country. */
  public static final Country GW = new ISOCountry(0x624, "GW", "GNB", "Guinea-Bissau", "Guinean");

  /** ISO 3166 country. */
  public static final Country GY = new ISOCountry(0x328, "GY", "GUY", "Guyana", "Guyanese");

  /** ISO 3166 country. */
  public static final Country HK = new ISOCountry(0x344, "HK", "HKG", "Hong Kong", "Hong Kong, Hongkongese");

  /** ISO 3166 country. */
  public static final Country HM = new ISOCountry(0x334, "HM", "HMD", "Heard Island and McDonald Islands");

  /** ISO 3166 country. */
  public static final Country HN = new ISOCountry(0x340, "HN", "HND", "Honduras", "Honduran");

  /** ISO 3166 country. */
  public static final Country HR = new ISOCountry(0x191, "HR", "HRV", "Croatia", "Croatian");

  /** ISO 3166 country. */
  public static final Country HT = new ISOCountry(0x332, "HT", "HTI", "Haiti", "Haitian");

  /** ISO 3166 country. */
  public static final Country HU = new ISOCountry(0x348, "HU", "HUN", "Hungary", "Hungarian");

  /** ISO 3166 country. */
  public static final Country ID = new ISOCountry(0x360, "ID", "IDN", "Indonesia", "Indonesian");

  /** ISO 3166 country. */
  public static final Country IE = new ISOCountry(0x372, "IE", "IRL", "Ireland", "Irish");

  /** ISO 3166 country. */
  public static final Country IL = new ISOCountry(0x376, "IL", "ISR", "Israel", "Israeli");

  /** ISO 3166 country. */
  public static final Country IM = new ISOCountry(0x833, "IM", "IMN", "Isle of Man", "Manx");

  /** ISO 3166 country. */
  public static final Country IN = new ISOCountry(0x356, "IN", "IND", "India", "Indian");

  /** ISO 3166 country. */
  public static final Country IO = new ISOCountry(0x086, "IO", "IOT", "British Indian Ocean Territory");

  /** ISO 3166 country. */
  public static final Country IQ = new ISOCountry(0x368, "IQ", "IRQ", "Iraq", "Iraqi");

  /** ISO 3166 country. */
  public static final Country IR = new ISOCountry(0x364, "IR", "IRN", "Iran Islamic Republic of", "Iranian, Persian");

  /** ISO 3166 country. */
  public static final Country IS = new ISOCountry(0x352, "IS", "ISL", "Iceland", "Icelandic");

  /** ISO 3166 country. */
  public static final Country IT = new ISOCountry(0x380, "IT", "ITA", "Italy", "Italian");

  /** ISO 3166 country. */
  public static final Country JE = new ISOCountry(0x832, "JE", "JEY", "Jersey");

  /** ISO 3166 country. */
  public static final Country JM = new ISOCountry(0x388, "JM", "JAM", "Jamaica", "Jamaican");

  /** ISO 3166 country. */
  public static final Country JO = new ISOCountry(0x400, "JO", "JOR", "Jordan", "Jordanian");

  /** ISO 3166 country. */
  public static final Country JP = new ISOCountry(0x392, "JP", "JPN", "Japan", "Japanese");

  /** ISO 3166 country. */
  public static final Country KE = new ISOCountry(0x404, "KE", "KEN", "Kenya", "Kenyan");

  /** ISO 3166 country. */
  public static final Country KG = new ISOCountry(0x417, "KG", "KGZ", "Kyrgyzstan", "Kyrgyz");

  /** ISO 3166 country. */
  public static final Country KH = new ISOCountry(0x116, "KH", "KHM", "Cambodia", "Cambodian");

  /** ISO 3166 country. */
  public static final Country KI = new ISOCountry(0x296, "KI", "KIR", "Kiribati", "I-Kiribati");

  /** ISO 3166 country. */
  public static final Country KM = new ISOCountry(0x174, "KM", "COM", "Comoros", "Comorian");

  /** ISO 3166 country. */
  public static final Country KN = new ISOCountry(0x659, "KN", "KNA", "Saint Kitts and Nevis");

  /** ISO 3166 country. */
  public static final Country KP = new ISOCountry(0x408, "KP", "PRK", "Korea Democratic People's Republic of", "North Korean");

  /** ISO 3166 country. */
  public static final Country KR = new ISOCountry(0x410, "KR", "KOR", "Korea Republic of", "South Korean");

  /** ISO 3166 country. */
  public static final Country KW = new ISOCountry(0x414, "KW", "KWT", "Kuwait", "Kuwaiti");

  /** ISO 3166 country. */
  public static final Country KY = new ISOCountry(0x136, "KY", "CYM", "Cayman Islands", "Caymanian");

  /** ISO 3166 country. */
  public static final Country KZ = new ISOCountry(0x398, "KZ", "KAZ", "Kazakhstan", "Kazakh");

  /** ISO 3166 country. */
  public static final Country LA = new ISOCountry(0x418, "LA", "LAO", "Lao People's Democratic Republic", "Lao");

  /** ISO 3166 country. */
  public static final Country LB = new ISOCountry(0x422, "LB", "LBN", "Lebanon", "Lebanese");

  /** ISO 3166 country. */
  public static final Country LC = new ISOCountry(0x662, "LC", "LCA", "Saint Lucia", "Saint Lucian");

  /** ISO 3166 country. */
  public static final Country LI = new ISOCountry(0x438, "LI", "LIE", "Liechtenstein");

  /** ISO 3166 country. */
  public static final Country LK = new ISOCountry(0x144, "LK", "LKA", "Sri Lanka", "Sri Lankan");

  /** ISO 3166 country. */
  public static final Country LR = new ISOCountry(0x430, "LR", "LBR", "Liberia", "Liberian");

  /** ISO 3166 country. */
  public static final Country LS = new ISOCountry(0x426, "LS", "LSO", "Lesotho", "Basotho");

  /** ISO 3166 country. */
  public static final Country LT = new ISOCountry(0x440, "LT", "LTU", "Lithuania", "Lithuanian");

  /** ISO 3166 country. */
  public static final Country LU = new ISOCountry(0x442, "LU", "LUX", "Luxembourg", "Luxembourg, Luxembourgish");

  /** ISO 3166 country. */
  public static final Country LV = new ISOCountry(0x428, "LV", "LVA", "Latvia", "Latvian");

  /** ISO 3166 country. */
  public static final Country LY = new ISOCountry(0x434, "LY", "LBY", "Libyan Arab Jamahiriya", "Libyan");

  /** ISO 3166 country. */
  public static final Country MA = new ISOCountry(0x504, "MA", "MAR", "Morocco", "Moroccan");

  /** ISO 3166 country. */
  public static final Country MC = new ISOCountry(0x492, "MC", "MCO", "Monaco", "Monegasque, Monacan");

  /** ISO 3166 country. */
  public static final Country MD = new ISOCountry(0x498, "MD", "MDA", "Moldova", "Moldovan");

  /** ISO 3166 country. */
  public static final Country ME = new ISOCountry(0x499, "ME", "MNE", "Montenegro", "Montenegrin");

  /** ISO 3166 country. */
  public static final Country MF = new ISOCountry(0x663, "MF", "MAF", "Saint Martin (French part)");

  /** ISO 3166 country. */
  public static final Country MG = new ISOCountry(0x450, "MG", "MDG", "Madagascar", "Malagasy");

  /** ISO 3166 country. */
  public static final Country MH = new ISOCountry(0x584, "MH", "MHL", "Marshall Islands", "Marshallese");

  /** ISO 3166 country. */
  public static final Country MK = new ISOCountry(0x807, "MK", "MKD", "Macedonia the former Yugoslav Republic of", "Macedonian");

  /** ISO 3166 country. */
  public static final Country ML = new ISOCountry(0x466, "ML", "MLI", "Mali", "Malian");

  /** ISO 3166 country. */
  public static final Country MM = new ISOCountry(0x104, "MM", "MMR", "Myanmar", "Burmese");

  /** ISO 3166 country. */
  public static final Country MN = new ISOCountry(0x496, "MN", "MNG", "Mongolia", "Mongolian");

  /** ISO 3166 country. */
  public static final Country MO = new ISOCountry(0x446, "MO", "MAC", "Macao", "Macanese, Chinese");

  /** ISO 3166 country. */
  public static final Country MP = new ISOCountry(0x580, "MP", "MNP", "Northern Mariana Islands", "Northern Marianan");

  /** ISO 3166 country. */
  public static final Country MQ = new ISOCountry(0x474, "MQ", "MTQ", "Martinique", "Martiniquais, Martinican");

  /** ISO 3166 country. */
  public static final Country MR = new ISOCountry(0x478, "MR", "MRT", "Mauritania", "Mauritanian");

  /** ISO 3166 country. */
  public static final Country MS = new ISOCountry(0x500, "MS", "MSR", "Montserrat", "Montserratian");

  /** ISO 3166 country. */
  public static final Country MT = new ISOCountry(0x470, "MT", "MLT", "Malta", "Maltese");

  /** ISO 3166 country. */
  public static final Country MU = new ISOCountry(0x480, "MU", "MUS", "Mauritius", "Mauritian");

  /** ISO 3166 country. */
  public static final Country MV = new ISOCountry(0x462, "MV", "MDV", "Maldives", "Maldivian");

  /** ISO 3166 country. */
  public static final Country MW = new ISOCountry(0x454, "MW", "MWI", "Malawi", "Malawian");

  /** ISO 3166 country. */
  public static final Country MX = new ISOCountry(0x484, "MX", "MEX", "Mexico", "Mexican");

  /** ISO 3166 country. */
  public static final Country MY = new ISOCountry(0x458, "MY", "MYS", "Malaysia", "Malaysian");

  /** ISO 3166 country. */
  public static final Country MZ = new ISOCountry(0x508, "MZ", "MOZ", "Mozambique", "Mozambican");

  /** ISO 3166 country. */
  public static final Country NA = new ISOCountry(0x516, "NA", "NAM", "Namibia", "Namibian");

  /** ISO 3166 country. */
  public static final Country NC = new ISOCountry(0x540, "NC", "NCL", "New Caledonia", "New Caledonian");

  /** ISO 3166 country. */
  public static final Country NE = new ISOCountry(0x562, "NE", "NER", "Niger", "Nigerien");

  /** ISO 3166 country. */
  public static final Country NF = new ISOCountry(0x574, "NF", "NFK", "Norfolk Island");

  /** ISO 3166 country. */
  public static final Country NG = new ISOCountry(0x566, "NG", "NGA", "Nigeria", "Nigerian");

  /** ISO 3166 country. */
  public static final Country NI = new ISOCountry(0x558, "NI", "NIC", "Nicaragua", "Nicaraguan");

  /** ISO 3166 country. */
  public static final Country NL = new ISOCountry(0x528, "NL", "NLD", "Netherlands", "Dutch");

  /** ISO 3166 country. */
  public static final Country NO = new ISOCountry(0x578, "NO", "NOR", "Norway", "Norwegian");

  /** ISO 3166 country. */
  public static final Country NP = new ISOCountry(0x524, "NP", "NPL", "Nepal", "Nepali");

  /** ISO 3166 country. */
  public static final Country NR = new ISOCountry(0x520, "NR", "NRU", "Nauru", "Nauruan");

  /** ISO 3166 country. */
  public static final Country NU = new ISOCountry(0x570, "NU", "NIU", "Niue", "Niuean");

  /** ISO 3166 country. */
  public static final Country NZ = new ISOCountry(0x554, "NZ", "NZL", "New Zealand");

  /** ISO 3166 country. */
  public static final Country OM = new ISOCountry(0x512, "OM", "OMN", "Oman", "Omani");

  /** ISO 3166 country. */
  public static final Country PA = new ISOCountry(0x591, "PA", "PAN", "Panama", "Panamanian");

  /** ISO 3166 country. */
  public static final Country PE = new ISOCountry(0x604, "PE", "PER", "Peru", "Peruvian");

  /** ISO 3166 country. */
  public static final Country PF = new ISOCountry(0x258, "PF", "PYF", "French Polynesia", "French Polynesian");

  /** ISO 3166 country. */
  public static final Country PG = new ISOCountry(0x598, "PG", "PNG", "Papua New Guinea", "Papua New Guinean, Papuan");

  /** ISO 3166 country. */
  public static final Country PH = new ISOCountry(0x608, "PH", "PHL", "Philippines", "Philippine, Filipino");

  /** ISO 3166 country. */
  public static final Country PK = new ISOCountry(0x586, "PK", "PAK", "Pakistan", "Pakistani");

  /** ISO 3166 country. */
  public static final Country PL = new ISOCountry(0x616, "PL", "POL", "Poland", "Polish");

  /** ISO 3166 country. */
  public static final Country PM = new ISOCountry(0x666, "PM", "SPM", "Saint Pierre and Miquelon", "Saint Pierrais, Miquelonnais");

  /** ISO 3166 country. */
  public static final Country PN = new ISOCountry(0x612, "PN", "PCN", "Pitcairn");

  /** ISO 3166 country. */
  public static final Country PR = new ISOCountry(0x630, "PR", "PRI", "Puerto Rico", "Puerto Rican");

  /** ISO 3166 country. */
  public static final Country PS = new ISOCountry(0x275, "PS", "PSE", "Palestinian Territory Occupied", "Palestinian");

  /** ISO 3166 country. */
  public static final Country PT = new ISOCountry(0x620, "PT", "PRT", "Portugal", "Portuguese");

  /** ISO 3166 country. */
  public static final Country PW = new ISOCountry(0x585, "PW", "PLW", "Palau", "Palauan");

  /** ISO 3166 country. */
  public static final Country PY = new ISOCountry(0x600, "PY", "PRY", "Paraguay", "Paraguayan");

  /** ISO 3166 country. */
  public static final Country QA = new ISOCountry(0x634, "QA", "QAT", "Qatar", "Qatari");

  /** ISO 3166 country. */
  public static final Country RE = new ISOCountry(0x638, "RE", "REU", "Reunion", "Reunionese, Reunionnais");

  /** ISO 3166 country. */
  public static final Country RO = new ISOCountry(0x642, "RO", "ROU", "Romania", "Romanian");

  /** ISO 3166 country. */
  public static final Country RS = new ISOCountry(0x688, "RS", "SRB", "Serbia", "Serbian");

  /** ISO 3166 country. */
  public static final Country RU = new ISOCountry(0x643, "RU", "RUS", "Russian Federation", "Russian");

  /** ISO 3166 country. */
  public static final Country RW = new ISOCountry(0x646, "RW", "RWA", "Rwanda", "Rwandan");

  /** ISO 3166 country. */
  public static final Country SA = new ISOCountry(0x682, "SA", "SAU", "Saudi Arabia", "Saudi, Saudi Arabian");

  /** ISO 3166 country. */
  public static final Country SB = new ISOCountry(0x090, "SB", "SLB", "Solomon Islands", "Solomon Island");

  /** ISO 3166 country. */
  public static final Country SC = new ISOCountry(0x690, "SC", "SYC", "Seychelles", "Seychellois");

  /** ISO 3166 country. */
  public static final Country SD = new ISOCountry(0x736, "SD", "SDN", "Sudan", "Sudanese");

  /** ISO 3166 country. */
  public static final Country SE = new ISOCountry(0x752, "SE", "SWE", "Sweden", "Swedish");

  /** ISO 3166 country. */
  public static final Country SG = new ISOCountry(0x702, "SG", "SGP", "Singapore");

  /** ISO 3166 country. */
  public static final Country SH = new ISOCountry(0x654, "SH", "SHN", "Saint Helena", "Saint Helenian");

  /** ISO 3166 country. */
  public static final Country SI = new ISOCountry(0x705, "SI", "SVN", "Slovenia", "Slovenian, Slovene");

  /** ISO 3166 country. */
  public static final Country SJ = new ISOCountry(0x744, "SJ", "SJM", "Svalbard and Jan Mayen");

  /** ISO 3166 country. */
  public static final Country SK = new ISOCountry(0x703, "SK", "SVK", "Slovakia", "Slovak");

  /** ISO 3166 country. */
  public static final Country SL = new ISOCountry(0x694, "SL", "SLE", "Sierra Leone", "Sierra Leonean");

  /** ISO 3166 country. */
  public static final Country SM = new ISOCountry(0x674, "SM", "SMR", "San Marino", "Sammarinese");

  /** ISO 3166 country. */
  public static final Country SN = new ISOCountry(0x686, "SN", "SEN", "Senegal", "Senegalese");

  /** ISO 3166 country. */
  public static final Country SO = new ISOCountry(0x706, "SO", "SOM", "Somalia", "Somali, Somalian");

  /** ISO 3166 country. */
  public static final Country SR = new ISOCountry(0x740, "SR", "SUR", "Suriname", "Surinamese");

  /** ISO 3166 country. */
  public static final Country ST = new ISOCountry(0x678, "ST", "STP", "Sao Tome and Principe", "Sao Tomean");

  /** ISO 3166 country. */
  public static final Country SV = new ISOCountry(0x222, "SV", "SLV", "El Salvador", "Salvadoran");

  /** ISO 3166 country. */
  public static final Country SY = new ISOCountry(0x760, "SY", "SYR", "Syrian Arab Republic", "Syrian");

  /** ISO 3166 country. */
  public static final Country SZ = new ISOCountry(0x748, "SZ", "SWZ", "Swaziland", "Swazi");

  /** ISO 3166 country. */
  public static final Country TC = new ISOCountry(0x796, "TC", "TCA", "Turks and Caicos Islands");

  /** ISO 3166 country. */
  public static final Country TD = new ISOCountry(0x148, "TD", "TCD", "Chad", "Chadian");

  /** ISO 3166 country. */
  public static final Country TF = new ISOCountry(0x260, "TF", "ATF", "French Southern Territories");

  /** ISO 3166 country. */
  public static final Country TG = new ISOCountry(0x768, "TG", "TGO", "Togo", "Togolese");

  /** ISO 3166 country. */
  public static final Country TH = new ISOCountry(0x764, "TH", "THA", "Thailand", "Thai");

  /** ISO 3166 country. */
  public static final Country TJ = new ISOCountry(0x762, "TJ", "TJK", "Tajikistan", "Tajikistani");

  /** ISO 3166 country. */
  public static final Country TK = new ISOCountry(0x772, "TK", "TKL", "Tokelau");

  /** ISO 3166 country. */
  public static final Country TL = new ISOCountry(0x626, "TL", "TLS", "Timor-Leste", "Timorese");

  /** ISO 3166 country. */
  public static final Country TM = new ISOCountry(0x795, "TM", "TKM", "Turkmenistan", "Turkmen");

  /** ISO 3166 country. */
  public static final Country TN = new ISOCountry(0x788, "TN", "TUN", "Tunisia", "Tunisian");

  /** ISO 3166 country. */
  public static final Country TO = new ISOCountry(0x776, "TO", "TON", "Tonga", "Tongan");

  /** ISO 3166 country. */
  public static final Country TR = new ISOCountry(0x792, "TR", "TUR", "Turkey", "Turkish");

  /** ISO 3166 country. */
  public static final Country TT = new ISOCountry(0x780, "TT", "TTO", "Trinidad and Tobago", "Trinidadian, Tobagonian");

  /** ISO 3166 country. */
  public static final Country TV = new ISOCountry(0x798, "TV", "TUV", "Tuvalu", "Tuvaluan");

  /** ISO 3166 country. */
  public static final Country TW = new ISOCountry(0x158, "TW", "TWN", "Taiwan Province of China", "Taiwanese");

  /** ISO 3166 country. */
  public static final Country TZ = new ISOCountry(0x834, "TZ", "TZA", "Tanzania United Republic of", "Tanzanian");

  /** ISO 3166 country. */
  public static final Country UA = new ISOCountry(0x804, "UA", "UKR", "Ukraine", "Ukrainian");

  /** ISO 3166 country. */
  public static final Country UG = new ISOCountry(0x800, "UG", "UGA", "Uganda", "Ugandan");

  /** ISO 3166 country. */
  public static final Country UM = new ISOCountry(0x581, "UM", "UMI", "United States Minor Outlying Islands");

  /** ISO 3166 country. */
  public static final Country US = new ISOCountry(0x840, "US", "USA", "United States", "American");

  /** ISO 3166 country. */
  public static final Country UY = new ISOCountry(0x858, "UY", "URY", "Uruguay", "Uruguayan");

  /** ISO 3166 country. */
  public static final Country UZ = new ISOCountry(0x860, "UZ", "UZB", "Uzbekistan", "Uzbekistani, Uzbek");

  /** ISO 3166 country. */
  public static final Country VA = new ISOCountry(0x336, "VA", "VAT", "Holy See (Vatican City State)");

  /** ISO 3166 country. */
  public static final Country VC = new ISOCountry(0x670, "VC", "VCT", "Saint Vincent and the Grenadines", "Saint Vincentian");

  /** ISO 3166 country. */
  public static final Country VE = new ISOCountry(0x862, "VE", "VEN", "Venezuela", "Venezuelan");

  /** ISO 3166 country. */
  public static final Country VG = new ISOCountry(0x092, "VG", "VGB", "Virgin Islands British", "Virgin Island");

  /** ISO 3166 country. */
  public static final Country VI = new ISOCountry(0x850, "VI", "VIR", "Virgin Islands U.S.", "Virgin Island");

  /** ISO 3166 country. */
  public static final Country VN = new ISOCountry(0x704, "VN", "VNM", "Viet Nam", "Vietnamese");

  /** ISO 3166 country. */
  public static final Country VU = new ISOCountry(0x548, "VU", "VUT", "Vanuatu", "Ni-Vanuatu, Vanuatuan");

  /** ISO 3166 country. */
  public static final Country WF = new ISOCountry(0x876, "WF", "WLF", "Wallis and Futuna", "Wallisian, Futunan");

  /** ISO 3166 country. */
  public static final Country WS = new ISOCountry(0x882, "WS", "WSM", "Samoa", "Samoan");

  /** ISO 3166 country. */
  public static final Country YE = new ISOCountry(0x887, "YE", "YEM", "Yemen", "Yemeni");

  /** ISO 3166 country. */
  public static final Country YT = new ISOCountry(0x175, "YT", "MYT", "Mayotte", "Mahoran");

  /** ISO 3166 country. */
  public static final Country ZA = new ISOCountry(0x710, "ZA", "ZAF", "South Africa", "South African");

  /** ISO 3166 country. */
  public static final Country ZM = new ISOCountry(0x894, "ZM", "ZMB", "Zambia", "Zambian");

  /** ISO 3166 country. */
  public static final Country ZW = new ISOCountry(0x716, "ZW", "ZWE", "Zimbabwe", "Zimbabwean");

  private static final Country[] VALUES = {
      AW, AF, AO, AI, AX, AL, AD, AN, AE, AR, AM, AS, AQ, TF, AG, AU,
      AT, AZ, BI, BE, BJ, BF, BD, BG, BH, BS, BA, BL, BY, BZ, BM, BO,
      BR, BB, BN, BT, BV, BW, CF, CA, CC, CH, CL, CN, CI, CM, CD, CG,
      CK, CO, KM, CV, CR, CU, CX, KY, CY, CZ, DE, DJ, DM, DK, DO, DZ,
      EC, EG, ER, EH, ES, EE, ET, FI, FJ, FK, FR, FO, FM, GA, GB, GE,
      GG, GH, GI, GN, GP, GM, GW, GQ, GR, GD, GL, GT, GF, GU, GY, HK,
      HM, HN, HR, HT, HU, ID, IM, IN, IO, IE, IR, IQ, IS, IL, IT, JM,
      JE, JO, JP, KZ, KE, KG, KH, KI, KN, KR, KW, LA, LB, LR, LY, LC,
      LI, LK, LS, LT, LU, LV, MO, MF, MA, MC, MD, MG, MV, MX, MH, MK,
      ML, MT, MM, ME, MN, MP, MZ, MR, MS, MQ, MU, MW, MY, YT, NA, NC,
      NE, NF, NG, NI, NU, NL, NO, NP, NR, NZ, OM, PK, PA, PN, PE, PH,
      PW, PG, PL, PR, KP, PT, PY, PS, PF, QA, RE, RO, RU, RW, SA, SD,
      SN, SG, GS, SH, SJ, SB, SL, SV, SM, SO, PM, RS, ST, SR, SK, SI,
      SE, SZ, SC, SY, TC, TD, TG, TH, TJ, TK, TM, TL, TO, TT, TN, TR,
      TV, TW, TZ, UG, UA, UM, UY, US, UZ, VA, VC, VE, VG, VI, VN, VU,
      WF, WS, YE, ZA, ZM, ZW };

  private int code;
  private String alpha2Code;
  private String alpha3Code;
  private String name;
  private String nationality;

  private ISOCountry(int code, String alpha2Code, String alpha3Code, String name) {
    this(code, alpha2Code, alpha3Code, name, name);
  }

  private ISOCountry(int code, String alpha2Code, String alpha3Code, String name, String nationality) {
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
    ISOCountry other = (ISOCountry)obj;
    return other.code == code;
  }

  public int compareTo(Country o) {
    return toAlpha2Code().compareTo(o.toAlpha2Code());
  }
}
