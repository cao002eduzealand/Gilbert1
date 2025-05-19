package Application;

import Domain.Brand;
import Infrastructure.BrandRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements ServiceInterface<Brand> {

    private final BrandRepositoryImpl repository;
    private final BrandRepositoryImpl brandRepositoryImpl;

    public BrandServiceImpl(BrandRepositoryImpl repository, BrandRepositoryImpl brandRepositoryImpl) {
        this.repository = repository;
        this.brandRepositoryImpl = brandRepositoryImpl;
    }

    @Override
    public Brand save(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Brand brand) {
        repository.update(brand);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Brand findById(int id) {
        return repository.findById(id);
    }

    public void saveBrands() {
        String rawBrands = """
                
                
                (di)vision
                032c
                1017 ALYX 9SM
                11 by Boris Bidjan Saberi
                132 5. ISSEY MIYAKE
                16Arlington
                3.1 Phillip Lim
                424
                44 Label Group
                4SDESIGNS
                66°North
                7 DAYS Active
                99%IS-
                _J.L - A.L_
                A BETTER FEELING
                A Diciannoveventitre
                A PERSONAL NOTE 73
                A-COLD-WALL*
                A. A. Spectrum
                A.P.C.
                AAPE by A Bathing Ape
                AARON ESH
                ABRA
                Achilles Ion Gabriel
                Acne Studios
                ACRONYM®
                ADER error
                adidas Originals
                adidas x Humanrace by Pharrell Williams
                Adieu
                Adsum
                Advisory Board Crystals
                ADYAR
                ænrmòus
                AFFXWRKS
                AFTER PRAY
                AGOLDE
                AGR
                Ahluwalia
                Ahluwalia &PaulSmith
                AIREI
                AKILA
                ALAINPAUL
                Alan Crocetti
                Alec Doherty
                ALEXANDER DIGENOVA
                Alexander Wang
                Alighieri
                ALTU
                AMBUSH
                AMI Paris
                AMIRI
                AMOMENTO
                and wander
                Anderson's
                Andersson Bell
                ANDREJ GRONAU
                Ann Demeulemeester
                Anna Sui
                ANONYMOUS CLUB
                ANOTHER ASPECT
                Apartment 1007
                APRÈS Research
                Archival Reinvent
                Aries
                Ashley Williams
                ASICS
                ASPESI
                ATON
                ATTACHMENT
                AURALEE
                AUTRY
                Aviva Jifei Xue
                Awake NY
                Axel Arigato
                Aztech Mountain
                B1ARCHIVE
                Baffin
                Baina
                Balenciaga
                Bally
                Balmain
                BAO BAO ISSEY MIYAKE
                BAPE
                Barbour
                Barena
                BARRAGÁN
                Bather
                BEAMS PLUS
                Belstaff
                Berner Kühl
                Bianca Saunders
                Billionaire Boys Club
                Birkenstock
                Birrot
                Black Comme des Garçons
                BLÆST
                Bless
                Bleue Burnham
                BLK DNM
                BLUEMARBLE
                Bode
                BONNIE CLYDE
                Boris Bidjan Saberi
                BOSS
                both
                Bottega Veneta
                Botter
                Brain Dead
                Brioni
                Brunello Cucinelli
                Burberry
                BUTLER SVC
                C.P. Company
                C2H4
                Calvin Klein
                CALVINLUO
                Camiel Fortgens
                CAMPERLAB
                Canada Goose
                Carhartt Work In Progress
                Carlota Barrera
                Carne Bollente
                CARNET-ARCHIVE
                CARRER
                Carter Young
                Cartier
                Casablanca
                CASEY CASEY
                Castañer
                CCP
                CDLP
                Celine
                CFCL
                Chanel\s
                Charles Jeffrey LOVERBOY
                Charlie Constantinou
                CHENPENG
                Children of the Discordance
                CHIMI
                Chopova Lowena
                Christian Louboutin
                Clarks Originals
                CMF Outdoor Garment
                CMMAWEAR
                CMMN SWDN
                Coach 1941
                Cobra S.C.
                colbo
                COLLEGIUM
                Collina Strada
                COMMAS
                Comme des Garçons Homme
                Comme des Garçons Homme Deux
                Comme des Garçons Homme Plus
                COMME des GARÇONS PLAY
                Comme des Garçons Shirt
                COMME des GARÇONS WALLETS
                Commission
                Common Projects
                Connor McKnight
                Converse
                COOR
                Coperni
                Cordera
                Cornerstone
                Côte&Ciel
                Courrèges
                COÛT DE LA LIBERTÉ
                Cowgirl Blue Co
                Craig Green
                Crocs
                Cutler and Gross
                DAIWA PIER39
                DANCER
                Danner
                DARKPARK
                Deadwood
                DEMON
                Descente ALLTERRAIN
                DEVÁ STATES
                DEVOA
                Diemme
                Diesel
                Dime
                Dingyun Zhang
                Dion Lee
                District Vision
                Dita
                DOCUMENT
                Dolce&Gabbana
                Double Rainbouu
                doublet
                Dr. Martens
                DRAE
                Drake's
                drew house
                Dries Van Noten
                Drôle De Monsieur
                Dsquared2
                Dunst
                ECCO.kollektive
                Eckhaus Latta
                Edward Cuming
                EGONlab
                Eidos
                ekn
                éliou
                Ellie Mercer
                Emanuele Bicocchi
                Emporio Armani
                Enfants Riches Déprimés
                Engineered Garments
                Entire Studios
                Erdem
                ERL
                Ernest W. Baker
                Études
                EYTYS
                F-LAGSTUF-F
                F/CE.®
                Fabiana Filippi
                FACTORY900
                FARIS
                Fax Copy Express
                Fear of God
                Fear of God Athletics
                Fear of God ESSENTIALS
                Fendi
                Feng Chen Wang
                Ferragamo
                FFFPOSTALSERVICE
                Filippa K
                Fiorucci
                FLATLIST EYEWEAR
                Floyd
                FORMA
                FRAME
                Fred Perry
                Frédérique Constant
                FREI-MUT
                Frenckenberger
                FUMITO GANRYU
                Gabriela Coll Garments
                Galilee-By-Sea
                GANNI
                GANT 240 MULBERRY STREET
                Garrett Leight
                GCDS
                Gentle Fullness
                Georgia Kemball
                Gerrit Jacob
                Gimaguas
                Giorgio Armani
                Givenchy
                Glass Cypress
                GmbH
                Golden Goose
                Goldwin
                Goldwin 0
                GR10K
                Gramicci
                Greg Lauren
                GREG ROSS
                Grey Ant
                Gucci
                GUESS USA
                Guest in Residence
                Guidi
                Han Kjobenhavn
                HARAGO
                Hatton Labs
                HAULIER
                HEAD OF STATE
                Hed Mayner
                HELIOT EMIL
                Helmut Lang
                Henrik Vibskov
                HEREU
                Hermes
                Heron Preston
                HGBB STUDIO
                HH-118389225
                HODAKOVA
                Holzweiler
                HOMME PLISSÉ ISSEY MIYAKE
                HOPE
                Horizn Studios
                Howlin'
                Hugo
                HUGO KREIT
                Human Recreational Services
                HYEIN SEO
                ICECREAM
                IN GOLD WE TRUST PARIS
                Innerraum
                INSATIABLE HIGH
                International Gallery BEAMS
                Isa Boulder
                Isabel Marant
                Izzue
                J EONGL I
                JACQUEMUS
                Jan-Jan Van Essche
                Jean Paul Gaultier
                JieDa
                Jil Sander
                JiyongKim
                John Elliott
                Johnlawrencesullivan
                Joseph
                Judy Turner
                Julius
                Juntae Kim
                Junya Watanabe
                Justine Clenquet
                Juun.J
                JW Anderson
                K.NGSLEY
                KANGHYUK
                KAPTAIN SUNSHINE
                KARA
                Karmuel Young
                Kartik Research
                KEEN
                Kenzo
                Khaite
                Khoki
                KIDILL
                Kids Worldwide
                KidSuper
                Kijun
                Kiko Kostadinov
                King & Tuckfield
                KLEMAN
                kolor
                KOZABURO
                Ksubi
                Kuboraum
                Kuro
                KUSIKOHC
                Labrum
                Lacoste
                Lady White Co.
                Lanvin
                Lardini
                Late Checkout
                Le Gramme
                le PÈRE
                LE17SEPTEMBRE
                LEMAIRE
                Les Tien
                lesugiatelier
                Levi's
                LGN Louis Gabriel Nouchi
                Li-Ning
                Liberal Youth Ministry
                LISA YANG
                LOEWE
                Louis Vuitton\s
                Loro Piana\s
                LOW CLASSIC
                Lownn
                LU'U DAN
                Ludovic de Saint Sernin
                Lukhanyo Mdingi
                MAAP
                MACKAGE
                Madhappy
                Magliano
                Maharishi
                Maiden Name
                Maisie Wilen
                Maison Kitsuné
                Maison Margiela
                Maison MIHARA YASUHIRO
                Manors Golf
                Manzoni 24
                MAPLE
                Marcelo Burlon County of Milan
                Marina Yee
                Marine Serre
                Mark Kenly Domino Tan Studio
                Marking Distance
                Marni
                Marsèll
                Martin Asbjørn
                Martine Ali
                Martine Rose
                Maryam Nassir Zadeh
                master-piece
                mastermind JAPAN
                MASTERMIND WORLD
                Matsuda
                MAUSTEIN
                Max Mara
                Maximilian Davis
                MCQ
                McQueen
                meanswhile
                Members of the Rage
                Merrell 1TRL
                Meryll Rogge
                Merz b. Schwanen
                Meta Campania Collective
                Metalwood Studio
                mfpen
                MICROMILSPEC
                MISBHV
                Missoni
                Missoni Sport
                Miu Miu\s
                MM6 Maison Margiela
                Moncler
                Moncler Genius
                Moncler Grenoble
                Monos
                Montblanc
                Moon Boot
                Moose Knuckles
                Moschino
                Mowalola
                Mr. Saturday
                MSGM
                Mugler
                Museum of Peace & Quiet
                N.Hoolywood
                Nahmias
                Naked & Famous Denim
                NAMESAKE
                nanamica
                NANGA
                Nanushka
                NEEDLES
                Neighborhood
                Neil Barrett
                New Balance
                Nike
                Nike Jordan
                Nike x OFF-White
                Nike x Travis Scott
                Nili Lotan
                NN07
                NO IDEA
                NO/FAITH STUDIOS
                Noah
                NOMA t.d.
                Noon Goons
                Norda
                NORSE PROJECTS
                Norse Projects ARKTISK
                NotSoNormal
                Nuba
                Nudie Jeans
                NULLUS
                Numbering
                NVRFRGT
                Oakley
                Oakley Factory Team
                OAS
                Objects IV Life
                octi
                Off-White
                Officine Creative
                Officine Générale
                Oliver Peoples
                Olly Shinder
                Omar Afridi
                On
                Online Ceramics
                Opening Ceremony
                Orlebar Brown
                Ottolinger
                OTTOMILA
                OUAT
                OUR LEGACY
                Outdoor Voices
                OVER OVER
                Palm Angels
                PALMER
                Palmes
                Paloma Wool
                PALY
                PANGAIA
                Parajumpers
                Paravel
                Parel Studios
                Parts of Four
                Pas Normal Studios
                Paul Smith
                PDF
                Pearl Octopuss.y
                Pearls Before Swine
                Perks and Mini
                Persol
                pet-tree-kor
                Peter Do
                Phileo
                Pilgrim Surf + Supply
                PLACES+FACES
                Polo Ralph Lauren
                PONDER.ER
                Pop Trading Company
                Port Tanger
                PORTER - Yoshida & Co
                POST ARCHIVE FACTION (PAF)
                POTTERY
                Prada Eyewear
                Praying
                Professor.E
                Prototypes
                PS by Paul Smith
                PUMA
                R13
                Rabanne
                Raf Simons
                rag & bone
                Raga Malak
                Raimundo Langlois
                RAINMAKER KYOTO
                RAINS
                Ralph Lauren Purple Label
                Random Identities
                RANRA
                Rapha
                Rassvet
                Ray-Ban
                RCOS
                Re/Done
                RECTO
                Reebok Classics
                Reese Cooper
                Reigning Champ
                Remi Relief
                Represent
                RETROSUPERFUTURE
                Rhude
                RICE NINE TEN
                Rick Owens
                Rick Owens DRKSHDW
                Rier
                RIGARDS
                Rimowa
                rito structure
                Rivington Roi Rebis
                ROA
                Robyn Lynch
                Róhe
                Rombaut
                RRL
                RTA
                S.R. STUDIO. LA. CA.
                sacai
                SAGE NATION
                Saint Laurent
                Saintwoods
                Salomon
                Samsøe Samsøe
                SAPIO
                Satisfy
                Satoshi Nakamoto
                Satta
                Saturdays NYC
                Saucony
                Saul Nash
                SC103
                Schnayderman's
                Sean Suen
                Sebago
                Sébline
                Second/Layer
                Secret of Manna
                Séfr
                Serapis
                Seventh
                Shamballa\s
                Simone Rocha
                SIR.
                Situationist
                SKIMS
                Sky High Farm Workwear
                Small Talk Studio
                Snow Peak
                Soar Running
                Solid Homme
                Song for the Mute
                Sophie Buhai
                SOPHNET.
                SOSHIOTSUKI
                Soulland
                South2 West8
                SPENCER BADU
                Spinelli Kilcollin
                Sporty & Rich
                Stanley Raffington
                S.T. Dupont
                Stefan Cooke
                stein
                Stepney Workers Club
                Steven Passaro
                Still Kelly
                Stockholm (Surfboard) Club
                Stolen Girlfriends Club
                Stone Island
                Story mfg.
                STRONGTHE
                Studio Nicholson
                Stüssy
                SUICOKE
                Sulvam
                Sunflower
                SUNNEI
                Sunspel
                Swampgod
                System
                T/SEHNE
                TAAKK
                Taiga Takahashi
                TAION
                TAKAHIROMIYASHITA TheSoloist.
                Tanaka
                Tekla
                Templa
                Teva
                Th products
                Thames MMXX.
                The Elder Statesman
                The Frankie Shop
                The Letters
                The North Face
                The Ouze
                The Row
                the Shepherd UNDERCOVER
                The Viridi-anne
                The World Is Your Oyster
                Theophilio
                Theory
                thisisneverthat
                Thom Browne
                thom/krom
                Thug Club
                Tiger of Sweden
                Timberland
                Title of Work
                TMS.SITE
                Toga Virilis
                Tods
                Tokyo James
                TOM FORD
                Tom Wood
                TOMBOGO™
                Tommy Jeans
                Tondolo
                Tonywack
                Toogood
                Tumi
                UMA WANG
                Umbro
                Uncertain Factor
                UNDERCOVER
                Uniform Bridge
                Universal Colours
                Universal Works
                untitlab®
                Valentino
                Valentino Garavani
                Van Cleef & Arpels\s
                Vans
                VAQUERA
                Vasiliki
                VEERT
                Veilance
                VEIN
                VEJA
                Veneda Carter
                Versace
                Versace Jeans Couture
                Versace Underwear
                VETEMENTS
                Viktor&Rolf Mister Mister
                Vince
                VINNY’s
                Virón
                visvim
                Vitaly
                Vivienne Westwood
                vowels
                VTMNTS
                WACKO MARIA
                Wales Bonner
                Walter Van Beirendonck
                We11done
                White Mountaineering®︎
                Who Decides War
                WILLY CHAVARRIA
                Won Hundred
                WOOD WOOD
                WOOYOUNGMI
                WTAPS
                WYNN HAMLYN
                Xander Zhou
                XENIA TELUNTS
                XLIM
                Y's For Men
                Y-3
                Y/Project
                YAKU
                YEEZY
                YLÈVE
                YMC
                YOHJI YAMAMOTO
                YOKE
                young n sang
                Youth
                Youths in Balaclava
                Yuki Hashimoto
                YULONG XIA
                YUME YUME
                Yves Saint Laurent
                Z Zegna
                ZANKOV
                ZEGNA
                ZEGNA x The Elder Statesman
                
                
                
                """;


        List<String> brandNames = Arrays.stream(rawBrands.split("\n"))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());


        for (String name : brandNames) {
            Brand brand = new Brand();
            brand.setBrandName(name);
            brandRepositoryImpl.save(brand);
        }
    }
}


