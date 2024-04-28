import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Top from './components/top/Top';

function App() {
  return (
    <div>
      <BrowserRouter>
        <div>
          <Routes>
            <Route path="/top" element={<Top />} />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
