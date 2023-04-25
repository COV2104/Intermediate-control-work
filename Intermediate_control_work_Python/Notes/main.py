from menu import Menu
import function as fn

if __name__ == "__main__":
    
    menuitems = [
        ("1", "Добавить новую заметку", fn.add_note),
        ("2", "Редактировать существующую заметку", fn.edit_note),
        ("3", "Удалить существующую заметку", fn.delete_note),
        ("4", "Фильтрация списка заметок по дате создания/обновления", fn.filter_notes_by_date),
        ("5", "Просмотреть все заметки", fn.view_all_notes),
        ("6", "Выход", lambda: exit())]

    menu = Menu(menuitems)
    menu.run('>: ')