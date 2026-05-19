package com.nallanudi.app.data.db

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.room.RoomOpenHelper
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.DBUtil
import androidx.room.util.FtsTableInfo
import androidx.room.util.TableInfo
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap
import java.util.HashSet
import javax.annotation.processing.Generated

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings(["unchecked", "deprecation"])
class AppDatabase_Impl : AppDatabase() {
    @kotlin.concurrent.Volatile
    private var _entryDao: EntryDao? = null

    @kotlin.concurrent.Volatile
    private var _myListDao: MyListDao? = null

    @kotlin.concurrent.Volatile
    private var _wordOfDayHistoryDao: WordOfDayHistoryDao? = null

    @Override
    @NonNull
    protected fun createOpenHelper(@NonNull config: DatabaseConfiguration): SupportSQLiteOpenHelper? {
        val _openCallback: SupportSQLiteOpenHelper.Callback =
            RoomOpenHelper(config, object : Delegate(1) {
                @Override
                fun createAllTables(@NonNull db: SupportSQLiteDatabase) {
                    db.execSQL("CREATE TABLE IF NOT EXISTS `entry` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `term` TEXT NOT NULL, `kannada_translation` TEXT NOT NULL, `kannada_explanation` TEXT NOT NULL, `example` TEXT NOT NULL, `subject` TEXT NOT NULL)")
                    db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_entry_term` ON `entry` (`term`)")
                    db.execSQL("CREATE TABLE IF NOT EXISTS `my_list` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `entry_id` INTEGER NOT NULL, `saved_at` INTEGER NOT NULL, FOREIGN KEY(`entry_id`) REFERENCES `entry`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
                    db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_my_list_entry_id` ON `my_list` (`entry_id`)")
                    db.execSQL("CREATE TABLE IF NOT EXISTS `word_of_day_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `entry_id` INTEGER NOT NULL, `date_shown` TEXT NOT NULL)")
                    db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_word_of_day_history_date_shown` ON `word_of_day_history` (`date_shown`)")
                    db.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `entry_fts` USING FTS4(`term` TEXT NOT NULL, content=`entry`)")
                    db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_entry_fts_BEFORE_UPDATE BEFORE UPDATE ON `entry` BEGIN DELETE FROM `entry_fts` WHERE `docid`=OLD.`rowid`; END")
                    db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_entry_fts_BEFORE_DELETE BEFORE DELETE ON `entry` BEGIN DELETE FROM `entry_fts` WHERE `docid`=OLD.`rowid`; END")
                    db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_entry_fts_AFTER_UPDATE AFTER UPDATE ON `entry` BEGIN INSERT INTO `entry_fts`(`docid`, `term`) VALUES (NEW.`rowid`, NEW.`term`); END")
                    db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_entry_fts_AFTER_INSERT AFTER INSERT ON `entry` BEGIN INSERT INTO `entry_fts`(`docid`, `term`) VALUES (NEW.`rowid`, NEW.`term`); END")
                    db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
                    db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3926c72a78f53d3d1a0b12180f8a9487')")
                }

                @Override
                fun dropAllTables(@NonNull db: SupportSQLiteDatabase) {
                    db.execSQL("DROP TABLE IF EXISTS `entry`")
                    db.execSQL("DROP TABLE IF EXISTS `my_list`")
                    db.execSQL("DROP TABLE IF EXISTS `word_of_day_history`")
                    db.execSQL("DROP TABLE IF EXISTS `entry_fts`")
                    val _callbacks: List<out RoomDatabase.Callback?>? = mCallbacks
                    if (_callbacks != null) {
                        for (_callback in _callbacks) {
                            _callback.onDestructiveMigration(db)
                        }
                    }
                }

                @Override
                fun onCreate(@NonNull db: SupportSQLiteDatabase?) {
                    val _callbacks: List<out RoomDatabase.Callback?>? = mCallbacks
                    if (_callbacks != null) {
                        for (_callback in _callbacks) {
                            _callback.onCreate(db)
                        }
                    }
                }

                @Override
                fun onOpen(@NonNull db: SupportSQLiteDatabase) {
                    mDatabase = db
                    db.execSQL("PRAGMA foreign_keys = ON")
                    internalInitInvalidationTracker(db)
                    val _callbacks: List<out RoomDatabase.Callback?>? = mCallbacks
                    if (_callbacks != null) {
                        for (_callback in _callbacks) {
                            _callback.onOpen(db)
                        }
                    }
                }

                @Override
                fun onPreMigrate(@NonNull db: SupportSQLiteDatabase?) {
                    DBUtil.dropFtsSyncTriggers(db)
                }

                @Override
                fun onPostMigrate(@NonNull db: SupportSQLiteDatabase) {
                    db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_entry_fts_BEFORE_UPDATE BEFORE UPDATE ON `entry` BEGIN DELETE FROM `entry_fts` WHERE `docid`=OLD.`rowid`; END")
                    db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_entry_fts_BEFORE_DELETE BEFORE DELETE ON `entry` BEGIN DELETE FROM `entry_fts` WHERE `docid`=OLD.`rowid`; END")
                    db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_entry_fts_AFTER_UPDATE AFTER UPDATE ON `entry` BEGIN INSERT INTO `entry_fts`(`docid`, `term`) VALUES (NEW.`rowid`, NEW.`term`); END")
                    db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_entry_fts_AFTER_INSERT AFTER INSERT ON `entry` BEGIN INSERT INTO `entry_fts`(`docid`, `term`) VALUES (NEW.`rowid`, NEW.`term`); END")
                }

                @Override
                @NonNull
                fun onValidateSchema(
                    @NonNull db: SupportSQLiteDatabase?
                ): RoomOpenHelper.ValidationResult? {
                    val _columnsEntry: HashMap<String?, TableInfo.Column?> =
                        HashMap<String?, TableInfo.Column?>(6)
                    _columnsEntry.put(
                        "id",
                        Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    _columnsEntry.put(
                        "term",
                        Column("term", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    _columnsEntry.put(
                        "kannada_translation",
                        Column(
                            "kannada_translation",
                            "TEXT",
                            true,
                            0,
                            null,
                            TableInfo.CREATED_FROM_ENTITY
                        )
                    )
                    _columnsEntry.put(
                        "kannada_explanation",
                        Column(
                            "kannada_explanation",
                            "TEXT",
                            true,
                            0,
                            null,
                            TableInfo.CREATED_FROM_ENTITY
                        )
                    )
                    _columnsEntry.put(
                        "example",
                        Column("example", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    _columnsEntry.put(
                        "subject",
                        Column("subject", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    val _foreignKeysEntry: HashSet<TableInfo.ForeignKey?> =
                        HashSet<TableInfo.ForeignKey?>(0)
                    val _indicesEntry: HashSet<TableInfo.Index?> = HashSet<TableInfo.Index?>(1)
                    _indicesEntry.add(
                        Index(
                            "index_entry_term",
                            true,
                            Arrays.asList("term"),
                            Arrays.asList("ASC")
                        )
                    )
                    val _infoEntry: TableInfo =
                        TableInfo("entry", _columnsEntry, _foreignKeysEntry, _indicesEntry)
                    val _existingEntry: TableInfo? = TableInfo.read(db, "entry")
                    if (!_infoEntry.equals(_existingEntry)) {
                        return ValidationResult(
                            false, ("entry(com.nallanudi.app.data.model.Entry).\n"
                                    + " Expected:\n" + _infoEntry + "\n"
                                    + " Found:\n" + _existingEntry)
                        )
                    }
                    val _columnsMyList: HashMap<String?, TableInfo.Column?> =
                        HashMap<String?, TableInfo.Column?>(3)
                    _columnsMyList.put(
                        "id",
                        Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    _columnsMyList.put(
                        "entry_id",
                        Column("entry_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    _columnsMyList.put(
                        "saved_at",
                        Column("saved_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    val _foreignKeysMyList: HashSet<TableInfo.ForeignKey?> =
                        HashSet<TableInfo.ForeignKey?>(1)
                    _foreignKeysMyList.add(
                        ForeignKey(
                            "entry",
                            "CASCADE",
                            "NO ACTION",
                            Arrays.asList("entry_id"),
                            Arrays.asList("id")
                        )
                    )
                    val _indicesMyList: HashSet<TableInfo.Index?> = HashSet<TableInfo.Index?>(1)
                    _indicesMyList.add(
                        Index(
                            "index_my_list_entry_id",
                            true,
                            Arrays.asList("entry_id"),
                            Arrays.asList("ASC")
                        )
                    )
                    val _infoMyList: TableInfo =
                        TableInfo("my_list", _columnsMyList, _foreignKeysMyList, _indicesMyList)
                    val _existingMyList: TableInfo? = TableInfo.read(db, "my_list")
                    if (!_infoMyList.equals(_existingMyList)) {
                        return ValidationResult(
                            false, ("my_list(com.nallanudi.app.data.model.MyListEntry).\n"
                                    + " Expected:\n" + _infoMyList + "\n"
                                    + " Found:\n" + _existingMyList)
                        )
                    }
                    val _columnsWordOfDayHistory: HashMap<String?, TableInfo.Column?> =
                        HashMap<String?, TableInfo.Column?>(3)
                    _columnsWordOfDayHistory.put(
                        "id",
                        Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    _columnsWordOfDayHistory.put(
                        "entry_id",
                        Column("entry_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    _columnsWordOfDayHistory.put(
                        "date_shown",
                        Column("date_shown", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY)
                    )
                    val _foreignKeysWordOfDayHistory: HashSet<TableInfo.ForeignKey?> =
                        HashSet<TableInfo.ForeignKey?>(0)
                    val _indicesWordOfDayHistory: HashSet<TableInfo.Index?> =
                        HashSet<TableInfo.Index?>(1)
                    _indicesWordOfDayHistory.add(
                        Index(
                            "index_word_of_day_history_date_shown",
                            true,
                            Arrays.asList("date_shown"),
                            Arrays.asList("ASC")
                        )
                    )
                    val _infoWordOfDayHistory: TableInfo = TableInfo(
                        "word_of_day_history",
                        _columnsWordOfDayHistory,
                        _foreignKeysWordOfDayHistory,
                        _indicesWordOfDayHistory
                    )
                    val _existingWordOfDayHistory: TableInfo? =
                        TableInfo.read(db, "word_of_day_history")
                    if (!_infoWordOfDayHistory.equals(_existingWordOfDayHistory)) {
                        return ValidationResult(
                            false,
                            ("word_of_day_history(com.nallanudi.app.data.model.WordOfDayHistory).\n"
                                    + " Expected:\n" + _infoWordOfDayHistory + "\n"
                                    + " Found:\n" + _existingWordOfDayHistory)
                        )
                    }
                    val _columnsEntryFts: HashSet<String?> = HashSet<String?>(1)
                    _columnsEntryFts.add("term")
                    val _infoEntryFts: FtsTableInfo = FtsTableInfo(
                        "entry_fts",
                        _columnsEntryFts,
                        "CREATE VIRTUAL TABLE IF NOT EXISTS `entry_fts` USING FTS4(`term` TEXT NOT NULL, content=`entry`)"
                    )
                    val _existingEntryFts: FtsTableInfo? = FtsTableInfo.read(db, "entry_fts")
                    if (!_infoEntryFts.equals(_existingEntryFts)) {
                        return ValidationResult(
                            false, ("entry_fts(com.nallanudi.app.data.model.EntryFts).\n"
                                    + " Expected:\n" + _infoEntryFts + "\n"
                                    + " Found:\n" + _existingEntryFts)
                        )
                    }
                    return ValidationResult(true, null)
                }
            }, "3926c72a78f53d3d1a0b12180f8a9487", "011386139c786b00d5ca712dd0646084")
        val _sqliteConfig: SupportSQLiteOpenHelper.Configuration? =
            SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name)
                .callback(_openCallback).build()
        val _helper: SupportSQLiteOpenHelper? = config.sqliteOpenHelperFactory.create(_sqliteConfig)
        return _helper
    }

    @Override
    @NonNull
    protected fun createInvalidationTracker(): InvalidationTracker {
        val _shadowTablesMap: HashMap<String?, String?> = HashMap<String?, String?>(1)
        _shadowTablesMap.put("entry_fts", "entry")
        val _viewTables: HashMap<String?, Set<String?>?> = HashMap<String?, Set<String?>?>(0)
        return InvalidationTracker(
            this,
            _shadowTablesMap,
            _viewTables,
            "entry",
            "my_list",
            "word_of_day_history",
            "entry_fts"
        )
    }

    @Override
    fun clearAllTables() {
        super.assertNotMainThread()
        val _db: SupportSQLiteDatabase = super.getOpenHelper().getWritableDatabase()
        val _supportsDeferForeignKeys =
            android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP
        try {
            if (!_supportsDeferForeignKeys) {
                _db.execSQL("PRAGMA foreign_keys = FALSE")
            }
            super.beginTransaction()
            if (_supportsDeferForeignKeys) {
                _db.execSQL("PRAGMA defer_foreign_keys = TRUE")
            }
            _db.execSQL("DELETE FROM `entry`")
            _db.execSQL("DELETE FROM `my_list`")
            _db.execSQL("DELETE FROM `word_of_day_history`")
            _db.execSQL("DELETE FROM `entry_fts`")
            super.setTransactionSuccessful()
        } finally {
            super.endTransaction()
            if (!_supportsDeferForeignKeys) {
                _db.execSQL("PRAGMA foreign_keys = TRUE")
            }
            _db.query("PRAGMA wal_checkpoint(FULL)").close()
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM")
            }
        }
    }

    @get:NonNull
    @get:Override
    protected val requiredTypeConverters: Map<Class<*>, List<Class<*>>?>
        get() {
            val _typeConvertersMap: HashMap<Class<*>?, List<Class<*>?>?> =
                HashMap<Class<*>?, List<Class<*>?>?>()
            _typeConvertersMap.put(EntryDao::class.java, EntryDao_Impl.getRequiredConverters())
            _typeConvertersMap.put(MyListDao::class.java, MyListDao_Impl.getRequiredConverters())
            _typeConvertersMap.put(
                WordOfDayHistoryDao::class.java,
                WordOfDayHistoryDao_Impl.getRequiredConverters()
            )
            return _typeConvertersMap
        }

    @get:NonNull
    @get:Override
    val requiredAutoMigrationSpecs: Set<Class<out AutoMigrationSpec>>
        get() {
            val _autoMigrationSpecsSet: HashSet<Class<out AutoMigrationSpec?>?> =
                HashSet<Class<out AutoMigrationSpec?>?>()
            return _autoMigrationSpecsSet
        }

    @Override
    @NonNull
    fun getAutoMigrations(
        @NonNull autoMigrationSpecs: Map<Class<out AutoMigrationSpec?>?, AutoMigrationSpec?>?
    ): List<Migration?> {
        val _autoMigrations: List<Migration?> = ArrayList<Migration?>()
        return _autoMigrations
    }

    @Override
    fun entryDao(): EntryDao? {
        if (_entryDao != null) {
            return _entryDao
        } else {
            synchronized(this) {
                if (_entryDao == null) {
                    _entryDao = EntryDao_Impl(this)
                }
                return _entryDao
            }
        }
    }

    @Override
    fun myListDao(): MyListDao? {
        if (_myListDao != null) {
            return _myListDao
        } else {
            synchronized(this) {
                if (_myListDao == null) {
                    _myListDao = MyListDao_Impl(this)
                }
                return _myListDao
            }
        }
    }

    @Override
    fun wordOfDayHistoryDao(): WordOfDayHistoryDao? {
        if (_wordOfDayHistoryDao != null) {
            return _wordOfDayHistoryDao
        } else {
            synchronized(this) {
                if (_wordOfDayHistoryDao == null) {
                    _wordOfDayHistoryDao = WordOfDayHistoryDao_Impl(this)
                }
                return _wordOfDayHistoryDao
            }
        }
    }
}