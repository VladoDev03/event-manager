import * as mediaService from '../services/mediaService';
import { useState } from 'react';

export function DeleteMedia() {
    const [mediaId, setMediaId] = useState('');

    const mediaHandler = e => {
        setMediaId(e.target.value);
    };

    const submitHandler = async (e) => {
        e.preventDefault();
        
        const result = await mediaService.deleteMedia(mediaId);
        console.log("Media URLs:", result);
    };

    return (
        <form onSubmit={submitHandler} method="post">
            <div>
                <label htmlFor="picture">Media Id</label>
                <input
                    id="media-id"
                    type="text"
                    name="media-id"
                    value={mediaId}
                    onChange={mediaHandler}
                    required
                />
            </div>
            <input type="submit" value="Delete" />
        </form>
    );
}
