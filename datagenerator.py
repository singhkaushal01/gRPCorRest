import csv
import random
from faker import Faker

fake = Faker()

# Function to generate random product data with 20 attributes
def generate_product_data():
    product_id = random.randint(100000, 999999)
    product_name = fake.word()
    price = round(random.uniform(10, 10000), 2)
    stock = random.randint(1, 500)
    category = fake.word()
    brand = fake.company()
    weight = f"{round(random.uniform(0.1, 50), 2)} kg"
    color = fake.color_name()
    manufacturer = fake.company()
    release_date = fake.date_between(start_date='-2y', end_date='today').strftime('%Y-%m-%d')
    rating = round(random.uniform(1, 5), 1)
    attribute_11 = fake.word()
    attribute_12 = fake.word()
    attribute_13 = fake.word()
    attribute_14 = fake.word()
    attribute_15 = fake.word()
    attribute_16 = fake.word()
    attribute_17 = fake.word()
    attribute_18 = fake.word()
    attribute_19 = fake.word()
    attribute_20 = fake.word()
    attribute_21 = fake.word()
    attribute_22 = fake.word()
    attribute_23 = fake.word()
    attribute_24 = fake.word()
    attribute_25 = fake.word()
    attribute_26 = fake.word()
    attribute_27 = fake.word()
    attribute_28 = fake.word()
    attribute_29 = fake.word()
    attribute_30 = fake.word()
    return [product_id, product_name, price, stock, category, brand, weight, color, manufacturer, release_date, rating,
            attribute_11, attribute_12, attribute_13, attribute_14, attribute_15, attribute_16, attribute_17,
            attribute_18, attribute_19, attribute_20, attribute_21, attribute_22, attribute_23, attribute_24,
            attribute_25, attribute_26, attribute_27, attribute_28, attribute_29, attribute_30]

# Generate 10000 rows of random product data with 20 attributes
data = []
for _ in range(10000):
    data.append(generate_product_data())

# Write data to CSV file
with open('product_data_20_attributes.csv', 'w', newline='') as csvfile:
    csv_writer = csv.writer(csvfile)
    csv_writer.writerow(['Product Id', 'Product Name', 'Price', 'Stock', 'Category', 'Brand', 'Weight', 'Color',
    'Manufacturer', 'Release Date', 'Rating', 'Attribute 11', 'Attribute 12', 'Attribute 13', 'Attribute 14',
    'Attribute 15', 'Attribute 16', 'Attribute 17', 'Attribute 18', 'Attribute 19', 'Attribute 20', 
    'Attribute 21', 'Attribute 22', 'Attribute 23', 'Attribute 24', 'Attribute 25', 'Attribute 26', 'Attribute 27', 
    'Attribute 28', 'Attribute 29', 'Attribute 30'])
    csv_writer.writerows(data)

print("CSV file 'product_data_20_attributes.csv' generated successfully with 10000 rows of random product data.")

