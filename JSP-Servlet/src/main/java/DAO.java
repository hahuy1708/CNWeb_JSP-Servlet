public class <EntityName>DAO {

    public <EntityName> getByID(String id) {
        <EntityName> obj = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM <table> WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new <EntityName>(
                    rs.getString("..."), // field1
                    rs.getString("..."), // field2
                    ...
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public ArrayList<<EntityName>> getAll() {
        ArrayList<<EntityName>> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM <table>";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new <EntityName>(
                    rs.getString("..."),
                    ...
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void insert(<EntityName> obj) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO <table>(field1, field2, ...) VALUES (?, ?, ...)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getField1());
            stmt.setString(2, obj.getField2());
            ...
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(<EntityName> obj) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE <table> SET field1 = ?, field2 = ? WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getField1());
            stmt.setString(2, obj.getField2());
            ...
            stmt.setString(n, obj.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM <table> WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int deleteAll(ArrayList<String> ids) {
        if (ids == null || ids.isEmpty()) return 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) sb.append("?,");
        sb.setLength(sb.length() - 1);

        String sql = "DELETE FROM <table> WHERE ID IN (" + sb + ")";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < ids.size(); i++) {
                stmt.setString(i + 1, ids.get(i));
            }
            return stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
