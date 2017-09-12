package com.gmail.jumpercorderosa.planetabuffet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gmail.jumpercorderosa.planetabuffet.model.Buffet;
import com.gmail.jumpercorderosa.planetabuffet.model.Group;
import com.gmail.jumpercorderosa.planetabuffet.model.Supplier;
import com.gmail.jumpercorderosa.planetabuffet.model.SupplierSegment;
import com.gmail.jumpercorderosa.planetabuffet.model.User;
import com.gmail.jumpercorderosa.planetabuffet.model.UserSupplier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielle on 13/08/2017.

 Lembrar::
 - Sqlite cria o banco no próprio celular, para atualizado preciso altera o valor da
 variável DATABASE_VERSION
 - O cursor é o meu resultset
 - Não esquecer de fechar o cursor
 - Contra sql injections (delete/update)
 */

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "buffet";

    // Contacts table name
    private static final String TABLE_EVENT_TYPE = "event_type";
    private static final String TABLE_USER = "user";
    private static final String TABLE_BUFFET_SEGMENT = "buffet_segment";
    private static final String TABLE_BUFFET = "buffet";
    private static final String TABLE_SUPPLIER = "supplier";
    private static final String TABLE_SUPPLIER_SEGMENT = "supplier_segment";
    private static final String TABLE_USER_SUPPLIER = "user_supplier";

    // Event Type Table Columns (Casamento/15 anos)
    private static final String EVENT_TYPE_ID = "event_type_id";
    private static final String EVENT_TYPE_DESC = "event_type_desc";

    // User Table Columns (Planeta Buffet/Tenor)
    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONE_NUMBER = "phone_number";
    private static final String FACEBOOK_ID = "facebook_id";
    private static final String USER_FLAG_ACTIVE = "flag_active";
    private static final String EVENT_DATE = "event_date";

    // Group Table Columns (Planeta Buffet/Tenor)
    private static final String BUFFET_SEGMENT_ID = "buffet_segment_id";
    private static final String BUFFET_SEGMENT_DESC = "buffet_segment_desc";

    // Supplier Segment Table Columns (Fotografia/Balões/Lembrancinhas)
    private static final String SUPPLIER_SEG_ID = "supplier_seg_id";
    private static final String SUPPLIER_SEG_DESC = "desc";
    private static final String SUPPLIER_SEG_IMG_NAME = "img_name";

    // Buffet Table Columns
    private static final String BUFFET_ID = "buffet_id";
    //private static final String BUFFET_SEGMENT_ID_FK = "BUFFET_SEGMENT_ID_fk";
    private static final String SUBSIDIARY = "subsidiary";
    private static final String BUFFET_ADDRESS = "address";
    private static final String BUFFET_ADDRESS_NUMBER = "address_number";
    private static final String BUFFET_CEP = "cep";
    private static final String BUFFET_PHONE_NUMBER = "phone_number";
    private static final String BUFFET_CNPJ = "cnpj";

    // Supplier Table Columns
    private static final String SUPPLIER_ID = "supplier_id";
    //private static final String SUPPLIER_SEG_ID_FK = "supplier_seg_id_FK";
    //private static final String BUFFET_ID = "buffet_id_FK";
    private static final String SUPPLIER_NAME = "name";
    private static final String SUPPLIER_CNPJ = "cnpj";
    private static final String SUPPLIER_CONTACT = "contact";
    private static final String SUPPLIER_PHONE_NUMBER = "phone_number";

    // User Supplier Table Columns
    private static final String USER_SUPPLIER_ID = "user_supplier_id";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String CREATE_EVENT_TYPE_TABLE = "CREATE TABLE " + TABLE_EVENT_TYPE + " ("
                    + EVENT_TYPE_ID + " INTEGER PRIMARY KEY, "
                    + EVENT_TYPE_DESC + " TEXT"
                    + ")";
            db.execSQL(CREATE_EVENT_TYPE_TABLE);

            String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " ("
                    + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LOGIN + " TEXT, "
                    + PASSWORD + " TEXT, "
                    + USER_EMAIL + " TEXT, "
                    + USER_PHONE_NUMBER + " TEXT, "
                    + EVENT_DATE + " TEXT, "
                    + EVENT_TYPE_ID + " INTEGER, "
                    + BUFFET_ID + " INTEGER"
                    + ")";
            db.execSQL(CREATE_USER_TABLE);

            String CREATE_BUFFET_SEGMENT_TABLE = "CREATE TABLE " + TABLE_BUFFET_SEGMENT + " ("
                    + BUFFET_SEGMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BUFFET_SEGMENT_DESC + " TEXT"
                    + ")";
            db.execSQL(CREATE_BUFFET_SEGMENT_TABLE);

            String CREATE_BUFFET_TABLE = "CREATE TABLE " + TABLE_BUFFET + " ("
                    + BUFFET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BUFFET_SEGMENT_ID + " INTEGER, "
                    + SUBSIDIARY + " TEXT, "
                    + BUFFET_ADDRESS + " TEXT, "
                    + BUFFET_ADDRESS_NUMBER + " TEXT, "
                    + BUFFET_CEP + " TEXT, "
                    + BUFFET_PHONE_NUMBER + " TEXT, "
                    + BUFFET_CNPJ + " TEXT"
                    + ")";
            db.execSQL(CREATE_BUFFET_TABLE);

            String CREATE_SUPPLIER_SEG_TABLE = "CREATE TABLE " + TABLE_SUPPLIER_SEGMENT + " ("
                    + SUPPLIER_SEG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SUPPLIER_SEG_DESC + " TEXT, "
                    + SUPPLIER_SEG_IMG_NAME + " TEXT"
                    + ")";
            db.execSQL(CREATE_SUPPLIER_SEG_TABLE);

            String CREATE_SUPPLIER_TABLE = "CREATE TABLE " + TABLE_SUPPLIER + " ("
                    + SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BUFFET_SEGMENT_ID + " INTEGER, "
                    + SUPPLIER_SEG_ID + " INTEGER, "
                    + SUPPLIER_NAME + " TEXT, "
                    + SUPPLIER_CONTACT + " TEXT, "
                    + SUPPLIER_PHONE_NUMBER + " TEXT"
                    + ")";
            db.execSQL(CREATE_SUPPLIER_TABLE);

            String CREATE_USER_SUPPLIERS_TABLE = "CREATE TABLE " + TABLE_USER_SUPPLIER + " ("
                    //+ USER_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + USER_ID + " INTEGER NOT NULL, "
                    + SUPPLIER_ID + " INTEGER NOT NULL"
                    + ")";
            db.execSQL(CREATE_USER_SUPPLIERS_TABLE);

            insert(db);

        } catch (Exception e) {
            Log.e("aux", "createTable exception", e);
        } finally {
            //    db.close();
        }

    }


    public void insert(SQLiteDatabase db) {

        try {

            String insert_aux;

            //INSERT EVENT_TYPE
            insert_aux = "";
            insert_aux = "INSERT INTO " + TABLE_EVENT_TYPE
                    + " (" + EVENT_TYPE_DESC + ")";

            db.execSQL(insert_aux
                    + " VALUES ('Wedding')");

            db.execSQL(insert_aux
                    + " VALUES ('Anniversary')");

            db.execSQL(insert_aux
                    + " VALUES ('Sweet Fifteen')");

            db.execSQL(insert_aux
                    + " VALUES ('Corporate Parties')");

            //INSERT GROUP
            insert_aux = "";
            insert_aux = "INSERT INTO " + TABLE_BUFFET_SEGMENT
                    + " (" + BUFFET_SEGMENT_DESC + ")";

            db.execSQL(insert_aux
                    + " VALUES ('Planeta Kids')");

            db.execSQL(insert_aux
                    + " VALUES ('Tenor')");

            //INSERT FRANQUIAS DE BUFFETS
            insert_aux = "";
            insert_aux = "INSERT INTO " + TABLE_BUFFET
                    + " (" + BUFFET_SEGMENT_ID
                    + ", " + SUBSIDIARY + ", " + BUFFET_ADDRESS + ", " + BUFFET_ADDRESS_NUMBER
                    + ", " + BUFFET_CEP + ", " + BUFFET_PHONE_NUMBER + ", " + BUFFET_CNPJ + ")";

            //PLANETA KIDS
            db.execSQL(insert_aux
                    + " VALUES (1, 'Alphaville', 'Al. Araguaia - Barueri - SP', '71', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Alto de Santana',' Rua Amaral Gama - São Paulo - SP', '303', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Anália Franco', 'Av. Vereador Abel Ferreira - São Paulo - SP', '114', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Alto da Lapa', 'Rua Cerro Corá - São Paulo - SP', '2232', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Guarulhos', 'Av. Timóteo Penteado - São Paulo - SP', '2899', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Jundiaí', 'Rua do Retiro - Vila das Hortencias - Jundiaí - SP', '2855', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Casa da Árvore', 'São Paulo - SP', '1075', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Santana', 'Av. Eng. Caetano Álvares - São Paulo - SP', '4070', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Tatuapé', 'Rua Antonio Camardo - São Paulo - SP', '103', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Pompéia', 'Av. Pompéia - São Paulo - SP', '1603', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Vila Romana', 'Rua Tito - São Paulo - SP', '980', '06663340', '(11) 2283-1997', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (1, 'Tatuape II', 'Rua Nova Jerusalém - São Paulo - SP', '623', '06663340', '(11) 2283-1997', '121161180000169' )");

            //TENOR
            db.execSQL(insert_aux
                    + " VALUES (2, 'Espaço Tenor', 'Rua Ponta Grossa - Mandaqui - São Paulo - SP', '251', '06663340', '(11) 2283-4275', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (2, 'Vila Bertolazzi', 'Rua Aurélia - São Paulo - SP', '281', '06663340', '(11) 2283-4275', '121161180000169' )");

            db.execSQL(insert_aux
                    + " VALUES (2, 'Tenor II', 'Avenida Luís Dumont Villares - Jardim - São Paulo - SP', '7047', '06663340', '(11) 2283-4275', '121161180000169' )");


            //INSERT SEGUIMENTO DOS FORNECEDORES
            insert_aux = "";
            insert_aux = "INSERT INTO " + TABLE_SUPPLIER_SEGMENT
                    + " (" + SUPPLIER_SEG_DESC
                    + ", " + SUPPLIER_SEG_IMG_NAME + ")";

            db.execSQL(insert_aux
                    + " VALUES ('Balões', 'ic_rv_balloons')"); //1

            db.execSQL(insert_aux
                    + " VALUES ('Bar', 'ic_rv_bar')"); //2

            db.execSQL(insert_aux
                    + " VALUES ('Carrinho de Doces', 'ic_rv_candy')"); //3

            db.execSQL(insert_aux
                    + " VALUES ('Cabine Fotográfica', 'ic_rv_photo_booth')"); //4

            db.execSQL(insert_aux
                    + " VALUES ('Foto Filmagem', 'ic_rv_filming')"); //4

            db.execSQL(insert_aux
                    + " VALUES ('Robô de Led', 'ic_rv_robot')"); //6

            db.execSQL(insert_aux
                    + " VALUES ('Cascata de Chocolate', 'ic_chocolate_cascade')"); //7

            //db.execSQL(insert_aux
            //        + " VALUES (8, 'Chopp', 'xxx.png')");

            db.execSQL(insert_aux
                    + " VALUES ('Decoração', 'ic_rv_decoration')"); //8

            db.execSQL(insert_aux
                    + " VALUES ('Filmagens Aéreas com Drone', 'ic_rv_drone')"); //9

            db.execSQL(insert_aux
                    + " VALUES ('Lembrancinhas', 'ic_rv_gift')"); //10

            //db.execSQL(insert_aux
            //        + " VALUES (12, Personagens, xxx.png)");

            db.execSQL(insert_aux
                    + " VALUES ('Coral', 'ic_rv_choral')"); //11

            db.execSQL(insert_aux
                    + " VALUES ('Passarela', 'ic_rv_runway')"); //12

            db.execSQL(insert_aux
                    + " VALUES ('Cerimonialista', 'ic_rv_cerimonialist')"); //13

            //db.execSQL(insert_aux
            //        + " VALUES (16, Bares)");

            db.execSQL(insert_aux
                    + " VALUES ('Alfaiataria', 'ic_rv_tailor')"); //14

            db.execSQL(insert_aux
                    + " VALUES ('Aluguel de Carros', 'ic_rv_car')"); //15

            db.execSQL(insert_aux
                    + " VALUES ('DJ', 'ic_rv_dj')"); //16

            db.execSQL(insert_aux
                    + " VALUES ('Retrospectiva', 'ic_rv_retrospective')"); //17


            //INSERT FORNECEDORES
            insert_aux = "";

            insert_aux = "INSERT INTO " + TABLE_SUPPLIER
                    + " (" + SUPPLIER_SEG_ID
                    + ", " + BUFFET_SEGMENT_ID + ", " + SUPPLIER_NAME
                    + ", " + SUPPLIER_CONTACT + ", " + SUPPLIER_PHONE_NUMBER
                    + ")";


            //FORNECEDORES PLANETA BUFFET
            //BALÕES ==> 1
            db.execSQL(insert_aux
                    + " VALUES (1, 1, 'Fagner Balões', 'Fagner', '95462-7470')");

            db.execSQL(insert_aux
                    + " VALUES (1, 1, 'Cristiana Balões', 'Cristina', '97143-4091')");

            //BARES ==> 2
            db.execSQL(insert_aux
                    + " VALUES (2, 1, 'Special Bar', 'Danilo', '96133-1116')");

            db.execSQL(insert_aux
                    + " VALUES (2, 1, 'Chopp Noroeste', 'Marcelo', '99339-2929')");

            //CARRINHO DE DOCES ==> 3
            db.execSQL(insert_aux
                    + " VALUES (3, 1, 'Mais um Brigadeiro', 'Rosimeire/Erika', '99835-6334')");

            db.execSQL(insert_aux
                    + " VALUES (3, 1, 'Sweet Churros', 'Wagner/Rosimeire', '(11) 94084-2383')");

            //CABINES FOTOGRAFICAS ==> 4
            db.execSQL(insert_aux
                    + " VALUES (4, 1, 'Mais Photos', 'Erika', '99224-0565')");

            db.execSQL(insert_aux
                    + " VALUES (4, 1, 'Star Photos', 'Marileide', '99609-7633')");

            //FOTO FILMAGEM ==> 5
            db.execSQL(insert_aux
                    + " VALUES (5, 1, 'Mais Photos', 'Erika', '99224-0565')");

            db.execSQL(insert_aux
                    + " VALUES (5, 1, 'Star Photos', 'Marileide', '99609-7633')");

            //ROBÔ DE LED ==> 6
            db.execSQL(insert_aux
                    + " VALUES (6, 1, 'Star Photos', 'Marileide', '99609-7633')");

            //CASCATA DE CHOCOLATE ==> 7
            db.execSQL(insert_aux
                    + " VALUES (7, 1, 'Choco e Delícia', 'Erika', '99434-9671')");

            //CHOPP ==> 8
            //db.execSQL(insert_aux
            //        + " VALUES (8, 1, 'Chopp Noroeste', 'Marcelo', '99339-2929')");

            //DECORACAO ==> 8
            db.execSQL(insert_aux
                    + " VALUES (8, 1, 'Vivo Desejo', 'Tatiana', '98090-7280')");

            db.execSQL(insert_aux
                    + " VALUES (8, 1, 'Letrix', 'Alexandre', '99770-0430')");

            //FILMAGENS AERES COM DRONE ==> 9
            db.execSQL(insert_aux
                    + " VALUES (9, 1, 'Star Photos', 'Wagner', '94084-2383')");

            //LEMBRANCINHAS ==> 11
            db.execSQL(insert_aux
                    + " VALUES (10, 1, 'Nana Banana', 'Ana', '99295-0024')");

            db.execSQL(insert_aux
                    + " VALUES (10, 1, 'Magic Stars', 'Simone', '97364-3003')");

            db.execSQL(insert_aux
                    + " VALUES (10, 1, 'Quinte Requinte', 'Wagner', '95352-7826')");

            //FORNECEDORES TENOR
            //CORAL ==> 11
            db.execSQL(insert_aux
                    + " VALUES (11, 2, 'Art EnCanto Coral e Orquestra', 'David Marçal', '94734-6525')");

            //PASSARELA ==> 12
            db.execSQL(insert_aux
                    + " VALUES (12, 2, 'Império Passarela Espelhada', 'Edgar', '90000-0000')");

            //CERIMONIALISTA ==> 13
            db.execSQL(insert_aux
                    + " VALUES (13, 2, 'Jefferon Paes Cerimonialista', 'Edgar', '98609-3560')");

            // ALFAIATARIA ==> 14
            db.execSQL(insert_aux
                    + " VALUES (14, 2, 'Victoria Alta Costura', 'Juliana', '94735-3000')");

            //DJ ==> 16
            db.execSQL(insert_aux
                    + " VALUES (16, 1, 'Star Photos', 'Wagner', '94084-2383')");

            //RETROSPECTIVA ==> 17
            db.execSQL(insert_aux
                    + " VALUES (17, 2, 'Star Photos', 'Marileide', '99609-7633')");

            //DECORACAO ==> 9
            //db.execSQL(insert_aux
            //        + " VALUES (9, 2, 'Flor de Liz Decorações', 'Regina', '99251-8866')");

            //CASCATA DE CHOCOLATE ==> 7
            //db.execSQL(insert_aux
             //       + " VALUES (7, 2, 'Choco e Delícia', 'Erika', '99434-9671')");


            //FOTO FILMAGEM ==> 5
            //db.execSQL(insert_aux
            //        + " VALUES (5, 2, 'Mais Photos', 'Erika', '99224-0565')");

            //db.execSQL(insert_aux
            //        + " VALUES (XXX, 5, 2, 'Star Photos', 'Marileide', '(11) 99609-7633')");

            //BARES ==> 2
           // db.execSQL(insert_aux
           //        + " VALUES (2, 1, 'Special Bar', 'Danilo', '96133-1116')");



            //ROBÔ DE LED ==> 6
            //db.execSQL(insert_aux
            //        + " VALUES (6, 1, 'Star Photos', 'Wagner', '94084-2383')");

            //CABINE FOTOGRÁFICAS ==> 4
            //db.execSQL(insert_aux
            //        + " VALUES (4, 2, 'Star Photos', 'Marileide', '99609-7633')");



        } catch (Exception e) {
            Log.e("aux", "insertRow exception", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_SUPPLIER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUFFET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUFFET_SEGMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIER_SEGMENT);

        // Create tables again
        onCreate(db);
    }

    //====================================================================
    // Adding new user
    //====================================================================
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Cursor cursor = db.query(TABLE_USER, new String[]{USER_ID,
                            LOGIN, PASSWORD, USER_EMAIL, USER_PHONE_NUMBER,
                            EVENT_DATE, EVENT_TYPE_ID, BUFFET_ID}, LOGIN + "=?",
                    new String[]{String.valueOf(user.getLogin())}, null, null, null, null);
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setLogin(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPhoneNumber(cursor.getString(4));
                //user.setFacebookId(cursor.getString(5));
                user.setEventDate(cursor.getString(5));
                user.setEventTypeId(Integer.parseInt(cursor.getString(6)));
                user.setBuffetId(Integer.parseInt(cursor.getString(7)));
                updateUser(user);

            } else {
                ContentValues values = new ContentValues();
                values.put(LOGIN, user.getLogin());
                values.put(PASSWORD, user.getPassword());
                values.put(USER_EMAIL, user.getEmail());
                values.put(USER_PHONE_NUMBER, user.getPhoneNumber());
                //values.put(FACEBOOK_ID, user.getFacebookId());
                //values.put(USER_FLAG_ACTIVE, user.isActiveFlag());
                values.put(EVENT_DATE, user.getEventDate());
                values.put(EVENT_TYPE_ID, user.getEventTypeId());
                values.put(BUFFET_ID, user.getBuffetId());

                // Inserting Row
                db.insert(TABLE_USER, null, values);
            }

        } catch (Exception e) {
            Log.e("aux", "selectRow exception", e);
        }

        db.close(); // Closing database connection

    }

    // Getting one user
    public User getUser(int id) {

        try {

            User user = null;

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_USER, new String[]{
                            USER_ID, LOGIN, PASSWORD, USER_EMAIL, USER_PHONE_NUMBER,
                            EVENT_DATE, EVENT_TYPE_ID, BUFFET_ID},
                    USER_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            //resultSet
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    user = new User(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            Integer.parseInt(cursor.getString(6)),
                            Integer.parseInt(cursor.getString(7))
                    );

                }
            }

            return user;
        } catch(Exception e) {
            Log.e("aux", "selectRow exception", e);
        }

        return null;
    }

    public int getUserId(String login) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                new String[]{USER_ID},
                LOGIN + "=?",
                new String[]{login}, null, null, null, null);

        //resultSet
        if (cursor != null)
            cursor.moveToFirst();

        return Integer.parseInt(cursor.getString(0));
    }

    // Getting one user
    public boolean checkUser(String _login, String _senha ) {

        boolean rc = false;
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_USER, new String[]{
                            USER_ID, LOGIN, PASSWORD, USER_EMAIL, USER_PHONE_NUMBER,
                            EVENT_DATE, EVENT_TYPE_ID, BUFFET_ID},
                    //LOGIN + " = ? AND " + PASSWORD + " = ?",
                    LOGIN + " = ?",
                    new String[]{_login}, null, null, null, null);

            //resultSet
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    rc = true;
                } else {
                    rc = false;
                }
            } else {
                rc = false;
            }
        } catch (Exception e) {
            Log.e("aux", "createTable exception", e);
        }

        return rc;
    }

    // Getting All Users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User (
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7))

                );
                //user.setId(Integer.parseInt(cursor.getString(0)));
                //user.setLogin(cursor.getString(1));
                //user.setPassword(cursor.getString(2));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        db.close();
        return userList;
    }

    // Getting users count
    public int getUsersCount() {
        int count;
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Updating a user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LOGIN, user.getLogin());
        values.put(PASSWORD, user.getPassword());
        values.put(EVENT_DATE, user.getEventDate());
        values.put(EVENT_TYPE_ID, user.getEventTypeId());
        values.put(BUFFET_ID, user.getBuffetId());

        // updating row
        int rc = db.update(TABLE_USER, values, USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        db.close();
        return rc;

    }

    // Deleting a user
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    //====================================================================
    // Adding new group
    //====================================================================
    public void addGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUFFET_SEGMENT_ID, group.getGroupId());
        values.put(BUFFET_SEGMENT_DESC, group.getGroupDesc());

        // Inserting Row
        db.insert(TABLE_BUFFET_SEGMENT, null, values);
        db.close(); // Closing database connection
    }

    // Getting one group
    public Group getGroup(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BUFFET_SEGMENT, new String[]{
                        BUFFET_SEGMENT_ID, BUFFET_SEGMENT_DESC},
                        BUFFET_SEGMENT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        //resultSet
        if (cursor != null)
            cursor.moveToFirst();

        Group group = new Group(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));

        // return group
        return group;
    }

    // Getting All Groups
    public List<Group> getAllGroups() {
        List<Group> groupList = new ArrayList<Group>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BUFFET_SEGMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Group group = new Group();
                group.setGroupId(Integer.parseInt(cursor.getString(0)));
                group.setGroupDesc(cursor.getString(1));

                // Adding contact to list
                groupList.add(group);
            } while (cursor.moveToNext());
        }

        // return group list
        db.close();
        return groupList;
    }

    // Getting users Count
    public int getGroupCount() {
        int count;
        String countQuery = "SELECT * FROM " + TABLE_BUFFET_SEGMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Updating a group
    public int updateGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUFFET_SEGMENT_DESC, group.getGroupDesc());

        // updating row
        int rc = db.update(TABLE_BUFFET_SEGMENT, values, BUFFET_SEGMENT_ID + " = ?",
                new String[]{String.valueOf(group.getGroupId())});

        db.close();
        return rc;
    }

    // Deleting a group
    public void deleteGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUFFET_SEGMENT, BUFFET_SEGMENT_ID + " = ?",
                new String[]{String.valueOf(group.getGroupId())});
        db.close();
    }


    //=====================================================================
    // Adding new buffet
    //=====================================================================
    public void addBuffet(Buffet buffet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUFFET_ID, buffet.getId());
        values.put(BUFFET_SEGMENT_ID, buffet.getBuffetSegmentId());
        values.put(SUBSIDIARY, buffet.getSubsidiary());
        values.put(BUFFET_ADDRESS, buffet.getAddress());
        values.put(BUFFET_ADDRESS_NUMBER, buffet.getNumber());
        values.put(BUFFET_CEP, buffet.getCep());
        values.put(BUFFET_PHONE_NUMBER, buffet.getPhoneNumber());
        values.put(BUFFET_CNPJ, buffet.getCnpj());

        // Inserting Row
        db.insert(TABLE_BUFFET, null, values);
        db.close(); // Closing database connection
    }

    // Getting one buffet
    public Buffet getBuffet(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BUFFET, new String[]{
                        BUFFET_ID, BUFFET_SEGMENT_ID, SUBSIDIARY, BUFFET_ADDRESS,
                        BUFFET_ADDRESS_NUMBER, BUFFET_CEP, BUFFET_PHONE_NUMBER,
                        BUFFET_CNPJ},
                BUFFET_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        //resultSet
        if (cursor != null)
            cursor.moveToFirst();

        Buffet buffet = new Buffet(
                Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),
                cursor.getString(3),
                Integer.parseInt(cursor.getString(4)),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7));

        // return buffet
        return buffet;
    }

    // Getting All Buffets
    public List<Buffet> getAllBuffets() {

        List<Buffet> buffetList = new ArrayList<Buffet>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BUFFET;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Buffet buffet = new Buffet();
                buffet.setId(Integer.parseInt(cursor.getString(0)));
                buffet.setBuffetSegmentId(Integer.parseInt(cursor.getString(1)));
                buffet.setSubsidiary(cursor.getString(2));
                buffet.setAddress(cursor.getString(3));
                buffet.setNumber(Integer.parseInt(cursor.getString(4)));
                buffet.setCep(cursor.getString(5));
                buffet.setPhoneNumber(cursor.getString(6));
                buffet.setCnpj(cursor.getString(7));

                // Adding contact to list
                buffetList.add(buffet);
            } while (cursor.moveToNext());
        }

        // return buffet list
        db.close();
        return buffetList;
    }

    // Getting buffets count
    public int getBuffetsCount() {
        int count;
        String countQuery = "SELECT * FROM " + TABLE_BUFFET;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Updating a buffet
    public int updateBuffet(Buffet buffet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBSIDIARY, buffet.getSubsidiary());
        values.put(BUFFET_ADDRESS, buffet.getAddress());
        values.put(BUFFET_ADDRESS_NUMBER, buffet.getNumber());
        values.put(BUFFET_CEP, buffet.getCep());
        values.put(BUFFET_PHONE_NUMBER, buffet.getNumber());
        values.put(BUFFET_CNPJ, buffet.getCnpj());

        // updating row
        int rc = db.update(TABLE_BUFFET, values, BUFFET_ID + " = ?",
                new String[]{String.valueOf(buffet.getId())});

        db.close();
        return rc;
    }

    // Deleting a buffet
    public void deleteBuffet(Buffet buffet) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUFFET, BUFFET_ID + " = ?",
                new String[]{String.valueOf(buffet.getId())});
        db.close();
    }

    //=====================================================================
    // Adding new supplier segment
    //=====================================================================
    public void addSupplierSegment(SupplierSegment supplier_seg) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUPPLIER_SEG_ID, supplier_seg.getSupplierSegmentId());
        values.put(SUPPLIER_SEG_DESC, supplier_seg.getSupplierSegmentDesc());

        // Inserting Row
        db.insert(TABLE_SUPPLIER_SEGMENT, null, values);
        db.close(); // Closing database connection
    }

    // Getting one buffet
    public SupplierSegment getSupplierSegment(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUPPLIER_SEGMENT, new String[]{
                        SUPPLIER_SEG_ID, SUPPLIER_SEG_DESC},
                SUPPLIER_SEG_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        //resultSet
        if (cursor != null)
            cursor.moveToFirst();

        SupplierSegment supplierSegment = new SupplierSegment(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));

        // return supplierSegment
        return supplierSegment;
    }

    // Getting All Suppliers
    public List<SupplierSegment> getAllSuppliersSegment() {

        List<SupplierSegment> supplierSegmentList = new ArrayList<SupplierSegment>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_SUPPLIER_SEGMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SupplierSegment supplierSegment = new SupplierSegment();
                supplierSegment.setSupplierSegmentId(Integer.parseInt(cursor.getString(0)));
                supplierSegment.setSupplierSegmentDesc(cursor.getString(1));
                supplierSegment.setSegmentImgName(cursor.getString(2));

                // Adding contact to list
                supplierSegmentList.add(supplierSegment);
            } while (cursor.moveToNext());
        }

        // return supplierSegmentList
        db.close();
        return supplierSegmentList;
    }

    // Getting supplierSegment count
    public int getSupplierSegmentCount() {
        int count;
        String countQuery = "SELECT * FROM " + TABLE_SUPPLIER_SEGMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Updating a supplierSegment
    public int updateSupplierSegment(SupplierSegment supplierSegment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUPPLIER_SEG_ID, supplierSegment.getSupplierSegmentId());
        values.put(SUPPLIER_SEG_DESC, supplierSegment.getSupplierSegmentDesc());

        // updating row
        int rc = db.update(TABLE_SUPPLIER_SEGMENT, values, SUPPLIER_SEG_ID + " = ?",
                new String[]{String.valueOf(supplierSegment.getSupplierSegmentId())});

        db.close();
        return rc;
    }

    // Deleting a buffet
    public void deleteSupplierSegment(SupplierSegment supplierSegment) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUPPLIER_SEGMENT, SUPPLIER_SEG_ID + " = ?",
                new String[]{String.valueOf(supplierSegment.getSupplierSegmentId())});
        db.close();
    }

    //=====================================================================
    // Adding new supplier
    //=====================================================================
    public void addSupplier(Supplier supplier) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUPPLIER_ID, supplier.getId());
        values.put(SUPPLIER_SEG_ID, supplier.getSegmentId());

        // Inserting Row
        db.insert(TABLE_SUPPLIER, null, values);
        db.close(); // Closing database connection
    }

    // Getting one supplier
    public Supplier getSupplier(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Supplier supplier = null;

        try {

            Cursor cursor = db.query(TABLE_SUPPLIER, new String[]{
                            SUPPLIER_ID, SUPPLIER_SEG_ID, BUFFET_SEGMENT_ID,
                            SUPPLIER_NAME, SUPPLIER_CONTACT, SUPPLIER_PHONE_NUMBER},
                    SUPPLIER_ID + " = ?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            //resultSet
            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    supplier = new Supplier(
                            Integer.parseInt(cursor.getString(0)),
                            Integer.parseInt(cursor.getString(1)),
                            Integer.parseInt(cursor.getString(2)),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5));
                }
            }
        } catch (Exception e) {
            Log.e("aux", "select exception", e);
        }
        return supplier;
    }

    // Getting All Suppliers
    public List<Supplier> getAllSuppliers() {

        List<Supplier> suppliersList = new ArrayList<Supplier>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_SUPPLIER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Supplier supplier = new Supplier();
                supplier.setId(Integer.parseInt(cursor.getString(0)));
                supplier.setSegmentId(Integer.parseInt(cursor.getString(1)));
                supplier.setBuffetId(Integer.parseInt(cursor.getString(2)));
                supplier.setName(cursor.getString(3));
                supplier.setCnpj(cursor.getString(4));
                supplier.setContact(cursor.getString(5));
                supplier.setPhoneNumber(cursor.getString(6));

                // Adding contact to list
                suppliersList.add(supplier);
            } while (cursor.moveToNext());
        }

        db.close();
        // return supplier list
        return suppliersList;
    }

    // Getting All Suppliers
    public List<Supplier> getAllSuppliersBySegment( int id_segment) {

        SQLiteDatabase db = this.getReadableDatabase();

        List<Supplier> suppliersList = new ArrayList<Supplier>();

        try {

            Cursor cursor = db.query(TABLE_SUPPLIER, new String[]{
                            SUPPLIER_ID, SUPPLIER_SEG_ID, BUFFET_SEGMENT_ID, SUPPLIER_NAME,
                            SUPPLIER_CONTACT, SUPPLIER_PHONE_NUMBER},
                    SUPPLIER_SEG_ID + "=?",
                    new String[]{String.valueOf(id_segment)}, null, null, null, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Supplier supplier = new Supplier();
                    supplier.setId(Integer.parseInt(cursor.getString(0)));
                    supplier.setSegmentId(Integer.parseInt(cursor.getString(1)));
                    supplier.setBuffetId(Integer.parseInt(cursor.getString(2)));
                    supplier.setName(cursor.getString(3));
                    //supplier.setCnpj(cursor.getString(4));
                    supplier.setContact(cursor.getString(4));
                    supplier.setPhoneNumber(cursor.getString(5));

                    // Adding contact to list
                    suppliersList.add(supplier);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.e("aux", "insertRow exception", e);
        }

        db.close();
        // return supplier list
        return suppliersList;
    }

    // Getting suppliers count
    public int getSuppliersCount() {
        int count;
        String countQuery = "SELECT * FROM " + TABLE_SUPPLIER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Updating a supplier
    public int updateSupplier(Supplier supplier) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUPPLIER_ID, supplier.getId());
        values.put(SUPPLIER_SEG_ID, supplier.getSegmentId());
        values.put(BUFFET_ID, supplier.getBuffetId());
        values.put(SUPPLIER_NAME, supplier.getName());
        values.put(SUPPLIER_CNPJ, supplier.getCnpj());
        values.put(SUPPLIER_CONTACT, supplier.getContact());
        values.put(SUPPLIER_PHONE_NUMBER, supplier.getPhoneNumber());

        // updating row
        int rc = db.update(TABLE_SUPPLIER, values, SUPPLIER_ID + " = ?",
                new String[]{String.valueOf(supplier.getId())});

        db.close();
        return rc;
    }

    // Deleting a supplier
    public void delete(Supplier supplier) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUPPLIER, SUPPLIER_ID + " = ?",
                new String[]{String.valueOf(supplier.getId())});
        db.close();
    }

    //====================================================================
    // Adding new user supplier
    //====================================================================
    public void addUserSupplier(UserSupplier userSupplier) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Cursor cursor = db.query(TABLE_USER_SUPPLIER, new String[]{
                            USER_ID, SUPPLIER_ID}, USER_ID + "=?",
                    new String[]{String.valueOf(userSupplier.getUserId())}, null, null, null, null);

            ContentValues values = new ContentValues();
            values.put(USER_ID, userSupplier.getUserId());
            values.put(SUPPLIER_ID, userSupplier.getSupplierId());

            // Inserting Row
            db.insert(TABLE_USER_SUPPLIER, null, values);

        } catch (Exception e) {
            Log.e("aux", "selectRow exception", e);
        }

        db.close(); // Closing database connection
    }

    // Getting All Users
    public List<UserSupplier> getAllUserSuppliers() {
        List<UserSupplier> userSuppliersList = new ArrayList<UserSupplier>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER_SUPPLIER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserSupplier userSupplier = new UserSupplier(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1))
                );

                // Adding contact to list
                userSuppliersList.add(userSupplier);
            } while (cursor.moveToNext());
        }

        // return contact list
        db.close();
        return userSuppliersList;
    }

    // Updating a user
    public int updateUserSupplier(UserSupplier userSupplier) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, userSupplier.getUserId());
        values.put(USER_SUPPLIER_ID, userSupplier.getSupplierId());

        // updating row
        int rc = db.update(TABLE_USER_SUPPLIER, values, USER_ID + " = ?",
                new String[]{String.valueOf(userSupplier.getUserId())});

        db.close();
        return rc;

    }

    // Deleting a user
    public void deleteUserSupplier(UserSupplier userSupplier) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_SUPPLIER, USER_ID + " = ?",
                new String[]{String.valueOf(userSupplier.getUserId())});
        db.close();
    }

}
