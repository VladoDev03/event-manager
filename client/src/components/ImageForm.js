import * as mediaService from '../services/mediaService';
import { useState } from 'react';

export function ImageForm() {
    const [files, setFiles] = useState(null);

    const submitHandler = async (e) => {
        e.preventDefault();

        if (!files) {
            console.error("No files selected");
            return;
        }

        const formData = new FormData();
        Array.from(files).forEach((file) => {
            formData.append("files", file);
        });

        try {
            const result = await mediaService.uploadImages(formData);
            console.log("Media URLs:", result);
        } catch (error) {
            console.error("Error uploading images:", error.message);
        }
    };

    const pictureHandler = (e) => {
        const selectedFiles = e.target.files;
        if (selectedFiles && selectedFiles.length > 0) {
            setFiles(selectedFiles);
        } else {
            setFiles(null);
        }
    };

    return (
        <form onSubmit={submitHandler} method="post" encType="multipart/form-data">
            <div>
                <label htmlFor="picture">Image</label>
                <input 
                    id="picture" 
                    type="file" 
                    name="picture" 
                    accept="image/*,video/*" 
                    multiple
                    onChange={pictureHandler} 
                    required 
                />
            </div>
            <input type="submit" value="Share" />
        </form>
    );
}
