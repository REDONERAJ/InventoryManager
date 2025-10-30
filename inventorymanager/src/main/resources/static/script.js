const baseUrl = "http://localhost:8080/api/items";

async function fetchItems() {
  const res = await fetch(baseUrl);
  const items = await res.json();
  const table = document.getElementById("tableBody");
  table.innerHTML = "";

  items.forEach((item) => {
    const row = document.createElement("tr");
    if (item.quantity < 5) row.classList.add("low-stock");
    row.innerHTML = `
      <td>${item.id}</td>
      <td>${item.name}</td>
      <td>${item.quantity}</td>
      <td>${item.price}</td>
      <td>${item.supplier}</td>
      <td>
        <button onclick="deleteItem(${item.id})">🗑️</button>
        <button onclick="sellItem(${item.id})">Sell 1</button>
      </td>
    `;
    table.appendChild(row);
  });
}

async function addItem() {
  const name = document.getElementById("name").value;
  const quantity = document.getElementById("quantity").value;
  const price = document.getElementById("price").value;
  const supplier = document.getElementById("supplier").value;

  await fetch(baseUrl, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, quantity, price, supplier }),
  });

  fetchItems();
}

async function deleteItem(id) {
  await fetch(`${baseUrl}/${id}`, { method: "DELETE" });
  fetchItems();
}

async function sellItem(id) {
  await fetch(`${baseUrl}/${id}/sell/1`, { method: "PUT" });
  fetchItems();
}

fetchItems();
