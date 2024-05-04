import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './components/login/Login';
import RedirectAuthorize from './components/redirectauthorize/RedirectAuthorize';

function App() {
  return (
    <div>
      <BrowserRouter>
        <div>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/redirect/authorize" element={<RedirectAuthorize />} />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
