const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');
const url = require('url');
const axios = require('axios');

function createWindow() {
  const mainWindow = new BrowserWindow({
    width: 1800,
    height: 1600,
    webPreferences: {
      preload: path.join(__dirname, './preload.js'),
      contextIsolation: true,
      enableRemoteModule: false,
    }
  });

  mainWindow.loadURL(url.format({
    pathname: path.join(__dirname, 'src/main/resources/static/frontend/cadastro/index.html'),
    protocol: 'file:',
    slashes: true
  }));

  mainWindow.webContents.on('did-finish-load', () => {
    fetchData().then(data => {
      mainWindow.webContents.send('fetch-data', data);
    }).catch(error => {
      console.error('Error fetching data:', error);
      mainWindow.webContents.send('fetch-error', error.message);
    });
  });
}

const axiosInstance = axios.create({
  headers: {
    'Content-Type': 'application/json; charset=UTF-8'
  }
});

async function fetchData() {
  try {
    const authResponse = await axiosInstance.post('http://127.0.0.1:8080/auth/login', {
      email: 'adm@gmail.com',
      senha: 'Admin'
    });
    const categoriesResponse = await axiosInstance.get('http://127.0.0.1:8080/categories');
    const projectsResponse = await axiosInstance.get('http://127.0.0.1:8080/projects');
    const tasksResponse = await axiosInstance.get('http://127.0.0.1:8080/tasks/groupedByCategory');
    const userAdmsResponse = await axiosInstance.get('http://127.0.0.1:8080/useradm');
    const usersResponse = await axiosInstance.get('http://127.0.0.1:8080/users');

    console.log('authResponse:', authResponse.data);
    console.log('categoriesResponse:', categoriesResponse.data);
    console.log('projectsResponse:', projectsResponse.data);
    console.log('tasksResponse:', tasksResponse.data);
    console.log('userAdmsResponse:', userAdmsResponse.data);
    console.log('usersResponse:', usersResponse.data);

    return {
      auth: authResponse.data,
      categories: categoriesResponse.data,
      projects: projectsResponse.data,
      tasks: tasksResponse.data,
      userAdms: userAdmsResponse.data,
      users: usersResponse.data,
    };
  } catch (error) {
    console.error('Error fetching data:', error.response ? error.response.data : error.message);
    throw error;
  }
}


app.whenReady().then(createWindow);

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

app.on('activate', () => {
  if (BrowserWindow.getAllWindows().length === 0) {
    createWindow();
  }
});
