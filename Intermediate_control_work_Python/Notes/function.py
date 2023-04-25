import json
import datetime
import random

notes = [] # создаем пустой список заметок


def add_note(): # функция добавления новой заметки
    id = len(notes) + random.randint(1,100) 
    title = input("Введите заголовок заметки: ")
    body = input("Введите текст заметки: ")
    created = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S") # текущее время
    note = {"id": id, "title": title, "body": body, "created": created, "updated": created}
    notes.append(note)
    save_notes()
    print(f"Заметка с id {id} успешно добавлена!")


def edit_note(): # функция редактирования заметки
    id = int(input("Введите id заметки для редактирования: "))
    for note in notes:
        if note["id"] == id:
            title = input(f"Введите новый заголовок для заметки {id}: ")
            body = input(f"Введите новый текст для заметки {id}: ")
            updated = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            note["title"] = title
            note["body"] = body
            note["updated"] = updated
            save_notes()
            print(f"Заметка с id {id} успешно обновлена!")
            return
    print(f"Заметка с id {id} не найдена")


def delete_note(): # функция удаления заметки
    id = int(input("Введите id заметки для удаления: "))
    for note in notes:
        if note["id"] == id:
            notes.remove(note)
            save_notes()
            print(f"Заметка с id {id} успешно удалена!")
            return
    print(f"Заметка с id {id} не найдена")


def filter_notes_by_date(): # функция фильтрации списка заметок по дате
    date = input("Введите дату в формате YYYY-MM-DD: ")
    filtered_notes = [note for note in notes if note["created"].startswith(date) or note["updated"].startswith(date)]
    if filtered_notes:
        for note in filtered_notes:
            print(f"Заметка {note['id']}: {note['title']}\n{note['body']}\nСоздано {note['created']} / Обновлено {note['updated']}\n")
    else:
        print("Заметок не найдено")


def save_notes(): # функция сохранения списка заметок в JSON файл
    with open("notes.json", "w") as file:
        json.dump(notes, file)


def load_notes(): # функция загрузки списка заметок из JSON файла
    try:
        with open("notes.json", "r") as file:
            return json.load(file)
    except FileNotFoundError:
        return []
    
def view_all_notes(): # функция просмотра списка зметок
    notes = load_notes()
    for note in notes:
        print(f"Заметка {note['id']}: {note['title']}\n{note['body']}\nСоздано {note['created']} / Обновлено {note['updated']}\n")    
    input("Нажмите Enter для выхода >: ")    