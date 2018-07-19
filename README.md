# KEY-PE-ALL
---

> **Introduction**
- This is the key PE in some FPT University courses
- From the 3 semester onwards
- Prepare `functions` that are available and transform some things
- Use only for reference purposes ( Do not depend on it )
- All of them are aggregated by my team from a variety of sources
> **My team**
1. [**Trần Quang Nhật**](https://github.com/jetaimefrc)
1. [**Đỗ Quang Hiệp**](https://github.com/bacodekiller) 
1. [**Phan Văn Đức**](https://github.com/phanduc0908)
1. [**Phạm Ngọc Hòa**](https://github.com/thaycacac)
1. [**Nguyễn Xuân Cường**](https://github.com/xuancuong98bn)
1. [**Nguyễn Tiến Mạnh**](https://github.com/manhnt94)
---
> **Review**
- **Key JavaDesk**
```java
public void initTableSQL(String sql, DefaultTableModel dftm, JTable table) {
        try {
            ResultSet rs = dao.getData(sql);
            Object[] title = {"ID", "Name", "Department Name", "Gender", "Age"};
            dftm.setColumnIdentifiers(title);
            dftm.setRowCount(0);
            hs.clear();
            while (rs.next()) {
                Vector vec = new Vector();
                vec.addElement(rs.getString(1));
                hs.add(rs.getString(1));
                vec.addElement(rs.getString(2));
                vec.addElement(rs.getString(3));
                vec.addElement((rs.getBoolean(4) == true) ? "Female" : "Male");
                vec.addElement(rs.getString(5));
                dftm.addRow(vec);
                table.setModel(dftm);
            }
            initProperties(rs);
        } catch (Exception es) {
            es.printStackTrace();
        }
    }
```
- **Key CSD**
```java
    /**
     * Remove xK biggest Person
     */
    public void removeSecond() {
        Person firstMax = getMaxPerson();
        if (firstMax == null) {
            return;
        }
        int n = size();
        if (n <= 1) {
            return;
        }
        int imax = 0;
        Node p = head;
        while (p != null && p.info.age == firstMax.age) {
            imax++;
            p = p.next;
        }
        // Find second max starting from imax
        Person secondMax = (p != null) ? p.info : null;
        for (int i = imax + 1; i < n; i++) {
            Node pi = getNode(i);
            if (pi.info.age > secondMax.age && pi.info.age != firstMax.age) {
                imax = i;
                secondMax = pi.info;
            }
        }
        if (imax < n) {
            remove(imax);
        }
    }
```

