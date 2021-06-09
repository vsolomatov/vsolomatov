package com.solomatoff.tracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tracker implements AutoCloseable {
    private TrackerProperty trackerProperty;
    private Connection conn;

    public Tracker(String config) {
        this.trackerProperty = new TrackerProperty(config);
        String url = trackerProperty.getProperty("url");
        String username = trackerProperty.getProperty("username");
        String password = trackerProperty.getProperty("password");
        String createitems = trackerProperty.getProperty("createitems");
        String createcomments = trackerProperty.getProperty("createcomments");
        try {
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Невозможно подключиться к базе данных " + url);
            e.printStackTrace();
            System.exit(1);
        }
        try (Statement st = conn.createStatement()) {
            // создаем таблицы
            st.execute(createitems);
            st.execute(createcomments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод добавляет новую заявку в список заявок (таблицу заявок в базе данных)
     *
     * @param item новая заявка
     * @return id добавленной заявки
     */
    public String add(Item item) {
        String insertItems = trackerProperty.getProperty("insertitems");
        String itemIdAsString = null;
        try (PreparedStatement stInsertItem = conn.prepareStatement(insertItems, Statement.RETURN_GENERATED_KEYS)) {
            stInsertItem.setString(1, item.getName());
            stInsertItem.setString(2, item.getDescription());
            stInsertItem.executeUpdate();
            ResultSet resultSet = stInsertItem.getGeneratedKeys();
            resultSet.next();
            // Полученное значение item_id из последовательности базы данных
            Integer itemId = resultSet.getInt(1);
            itemIdAsString = Integer.toString(itemId);
            resultSet.close();
            // Добавляем комментарии этой заявки в таблицу comments
            String[] comments = item.getComments();
            if (comments != null) {
                for (String comment : comments) {
                    commentInsert(itemId, comment);
                }
            }
        } catch (NullPointerException | SQLException e) {
            System.out.println("Невозможно добавить новую заявку " + item.getName() + " " + item.getDescription());
            e.printStackTrace();
        }
        return itemIdAsString;
    }

    /**
     * Добавляет один комментарий в таблицу комментариев
     *
     * @param itemId      Идентификатор заявки
     * @param commentText текст комментария
     */
    private void commentInsert(Integer itemId, String commentText) {
        String insertComments = trackerProperty.getProperty("insertcomments");
        try (PreparedStatement stInsertComment = conn.prepareStatement(insertComments)) {
            stInsertComment.setString(1, commentText);
            stInsertComment.setInt(2, itemId);
            stInsertComment.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Невозможно добавить комментарий в заявку " + itemId);
            e.printStackTrace();
        }
    }

    /**
     * Добавляет комментарии в таблицу комментариев (из массива)
     *
     * @param id            Идентификатор заявки
     * @param commentsArray массив комментариев
     */
    public void commentsInsert(String id, String[] commentsArray) {
        if (commentsArray != null) {
            Integer itemId;
            for (String comment : commentsArray) {
                itemId = Integer.parseInt(id);
                commentInsert(itemId, comment);
            }
        }
    }

    /**
     * Обновляет уже имеющуюся заявку с идентификатором id
     *
     * @param id   Идентификатор заявки
     * @param item новая заявка
     */
    public void replace(String id, Item item) {
        String updateItems = trackerProperty.getProperty("updateitems");
        try (PreparedStatement stUpdareItem = conn.prepareStatement(updateItems + " WHERE item_id=?")) {
            stUpdareItem.setString(1, item.getName());
            stUpdareItem.setString(2, item.getDescription());
            Integer itemId = Integer.parseInt(id);
            stUpdareItem.setInt(3, itemId);
            stUpdareItem.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Невозможно обновить заявку " + id);
            e.printStackTrace();
        }
    }

    /**
     * Удаляет уже имеющуюся заявку с идентификатором id
     *
     * @param id Идентификатор заявки
     */
    public void delete(String id) {
        String deleteComments = trackerProperty.getProperty("deletecomments");
        try (PreparedStatement stDeleteComments = conn.prepareStatement(deleteComments + " WHERE comment_item_id=?")) {
            Integer itemId = Integer.parseInt(id);
            stDeleteComments.setInt(1, itemId);
            stDeleteComments.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Невозможно удалить комментарии заявки " + id);
            e.printStackTrace();
        }
        String deleteItems = trackerProperty.getProperty("deleteitems");
        try (PreparedStatement stDeleteItems = conn.prepareStatement(deleteItems + " WHERE item_id=?")) {
            Integer itemId = Integer.parseInt(id);
            stDeleteItems.setInt(1, itemId);
            stDeleteItems.executeUpdate();
        } catch (SQLException e) {
            //System.out.println("Невозможно удалить заявку " + id);
            e.printStackTrace();
        }
    }

    /**
     * Удаляет все имеющиеся заявки
     */
    public void deleteAll() {
        String deleteComments = trackerProperty.getProperty("deletecomments");
        try (PreparedStatement stDeleteComments = conn.prepareStatement(deleteComments)) {
            stDeleteComments.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Невозможно удалить комментарии ВСЕХ заявок");
            e.printStackTrace();
        }
        String deleteItems = trackerProperty.getProperty("deleteitems");
        try (PreparedStatement stDeleteItems = conn.prepareStatement(deleteItems)) {
            stDeleteItems.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Невозможно удалить ВСЕ заявки");
            e.printStackTrace();
        }
    }

    private Item createItemForTracker(Integer itemId, String itemName, String itemDescription, Date itemCreated) {
        Item item = new Item(null, null, null, null, null);
        item.setId(itemId);
        item.setName(itemName);
        item.setDescription(itemDescription);
        item.setCreated(itemCreated);
        // Вначале определим количество комментариев
        String selectcountcomments = trackerProperty.getProperty("selectcountcomments");
        int sizeComments = 0;
        try (PreparedStatement stComments = conn.prepareStatement(selectcountcomments + " WHERE comment_item_id=?")) {
            stComments.setInt(1, itemId);
            ResultSet rsComments = stComments.executeQuery();
            rsComments.next();
            sizeComments = rsComments.getInt(1);
            rsComments.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Теперь заполняем массив комментариев
        String selectcomments = trackerProperty.getProperty("selectcomments");
        String[] comments = new String[sizeComments];
        int index = 0;
        try (PreparedStatement stComments = conn.prepareStatement(selectcomments + " WHERE comment_item_id =?")) {
            stComments.setInt(1, item.getId());
            ResultSet rsComments = stComments.executeQuery();
            while (rsComments.next()) {
                comments[index] = rsComments.getString(2);
                index++;
            }
            rsComments.close();
            // Заполняем в item поле comments
            item.setComments(comments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Находит заявку с идентификатором id
     *
     * @param id Идентификатор заявки
     * @return ссылку на найденную заявку или null
     */
    public Item findById(String id) {
        Item item = null;
        if (id != null) {
            String selectitems = trackerProperty.getProperty("selectitems");
            try (PreparedStatement stItems = conn.prepareStatement(selectitems + " WHERE item_id=?")) {
                Integer itemId = Integer.parseInt(id);
                stItems.setInt(1, itemId);
                // делаем выборку из таблицы items
                ResultSet rs = stItems.executeQuery();
                if (rs.next()) {
                    item = createItemForTracker(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
                }
                rs.close();
            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }


    /**
     * Находит заявку с именем name
     *
     * @param name имя заявки
     * @return ссылку на найденную заявку или null
     */
    public List<Item> findByName(String name) {
        if (name == null) {
            name = "NULL";
        }
        ArrayList<Item> result = new ArrayList<>();
        Item item;
        String selectitems = trackerProperty.getProperty("selectitems");
        try (PreparedStatement st = conn.prepareStatement(selectitems + " WHERE item_name=?")) {
            st.setString(1, name);
            // делаем выборку из таблицы items
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                item = createItemForTracker(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
                result.add(item);
            }
            rs.close();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод возвращает список всех заявок
     *
     * @return ссылку на список items
     */
    public List<Item> findAll() {
        ArrayList<Item> result = new ArrayList<>();
        Item item;
        String selectitems = trackerProperty.getProperty("selectitems");
        try (Statement st = conn.createStatement()) {
            // делаем выборку из таблицы items
            ResultSet rs = st.executeQuery(selectitems + " order by item_id");
            while (rs.next()) {
                item = createItemForTracker(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
                result.add(item);
            }
            rs.close();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }
}
