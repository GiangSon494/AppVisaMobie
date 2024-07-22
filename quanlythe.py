# Dữ liệu ban đầu
cards = [
    {"id": 1, "name": "Visa", "active": True},
    {"id": 2, "name": "Mastercard", "active": True},
    {"id": 3, "name": "Amex", "active": True}
]

# Hàm chọn thẻ
def select_card(card_id):
    for card in cards:
        if card['id'] == card_id:
            return card
    return None

# Hàm xóa thẻ
def delete_card(card_id):
    global cards
    cards = [card for card in cards if card['id'] != card_id]

# Sử dụng các hàm
selected_card = select_card(1)
print("Selected Card:", selected_card)

delete_card(2)
print("Remaining Cards:", cards)
